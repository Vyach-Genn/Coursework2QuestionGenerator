package coursework2questiongenerator.service;

import coursework2questiongenerator.domain.Question;
import coursework2questiongenerator.exception.TooManyQuestionsRequestedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl out;

    @Test
    void getSoAmountQuestionsAndAnswers_ShouldReturnCorrectAmountAndQuestions() {
        Set<Question> questions = new HashSet<>();
        questions.add(new Question("Что такое переменная", "Именованная область памяти"));
        questions.add(new Question("Что такое Java", "Язык программирования"));
        questions.add(new Question("Что такое объект", "Экземпляр класса"));

        when(javaQuestionService.getRandomQuestions(3)).thenReturn(questions);
        Set<Question> actual = out.getSoAmountQuestionsAndAnswers(3);

        assertThat(actual).hasSize(3)
                .containsAll(questions);
        verify(javaQuestionService, times(1)).getRandomQuestions(3);
    }

    @Test
    void getSoAmountQuestionsAndAnswers_ShouldExceptionsWhenQuestionNullAndAnswerNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            out.getSoAmountQuestionsAndAnswers(0);
        });
    }
    @Test
    void getSoAmountQuestionsAndAnswers_ShouldThrowException_WhenAmountExceedsAvailableQuestions() {
        when(javaQuestionService.getRandomQuestions(10))
                .thenThrow(new TooManyQuestionsRequestedException("Запрошено больше вопросов, чем есть в сервисе"));

        assertThrows(TooManyQuestionsRequestedException.class, () -> {
            out.getSoAmountQuestionsAndAnswers(10);
        });
    }
}