package coursework.questiongenerator.service;

import coursework.questiongenerator.domain.Question;
import coursework.questiongenerator.exception.TooManyQuestionsRequestedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl out;

    @Test
    void getQuestions_ShouldReturnCorrectAmountOfQuestions() {
        when(javaQuestionService.getAll()).thenReturn(Set.of(
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 2", "Ответ 2"),
                new Question("Вопрос 3", "Ответ 3")));
        when(javaQuestionService.getRandomQuestions()).thenReturn(
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 2", "Ответ 2"));

        Collection<Question> questions = out.getQuestions(2);

        assertEquals(2, questions.size());
        verify(javaQuestionService, times(2)).getRandomQuestions();
    }

    @Test
    void getQuestions_ShouldThrowException_WhenAmountIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> out.getQuestions(-1));
        assertThrows(IllegalArgumentException.class, () -> out.getQuestions(0));
        assertThrows(TooManyQuestionsRequestedException.class, () -> out.getQuestions(4));
    }

    @ParameterizedTest
    @CsvSource({"0, 5", "-1, 10"})
    void validateAmount_ShouldThrowIllegalArgumentException_WhenAmountIsZeroOrNegative(int amount, int maxSize) {
        assertThrows(IllegalArgumentException.class, () -> out.validateAmount(amount, maxSize));
    }

    @ParameterizedTest
    @CsvSource({"6, 5", "11, 10"})
    void validateAmount_ShouldThrowTooManyQuestionsRequestedException_WhenAmountExceedsMaxSize(int amount, int maxSize) {
        assertThrows(TooManyQuestionsRequestedException.class, () -> out.validateAmount(amount, maxSize));
    }

    @ParameterizedTest
    @CsvSource({"1, 5", "3, 10", "10, 10"})
    void validateAmount_ShouldNotThrowException_WhenAmountIsValid(int amount, int maxSize) {
        assertDoesNotThrow(() -> out.validateAmount(amount, maxSize));
    }

}