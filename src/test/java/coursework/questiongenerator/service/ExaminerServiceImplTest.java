package coursework.questiongenerator.service;

import coursework.questiongenerator.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl out;

    private final List<Question> sampleQuestions = List.of(
            new Question("Что такое объект", "Экземпляр класса"),
            new Question("Что такое полиморфизм?", "Одно из основных свойств ООП"),
            new Question("Что такое массив?", "Структура данных"));

    @BeforeEach
    void setUp() {
        when(javaQuestionService.getRandomQuestions(anyInt())).thenReturn(Set.copyOf(sampleQuestions));
    }

    @Test
    void getAndQuestions_ShouldReturnExactAmountOfQuestions() {
        int amount = 3;

        Collection<Question> actual = out.getQuestions(amount);

        assertThat(actual).hasSize(3);
        verify(javaQuestionService, times(1)).getRandomQuestions(amount);
    }

    @Test
    void getQuestions_ShouldReturnUniqueQuestions() {
        int amount = 2;

        Collection<Question> actual = out.getQuestions(amount);

        assertThat(actual).doesNotHaveDuplicates();
        verify(javaQuestionService, times(1)).getRandomQuestions(2);
    }

}