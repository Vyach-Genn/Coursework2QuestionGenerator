package coursework2.questiongenerator.service;

import coursework2.questiongenerator.domain.Question;
import coursework2.questiongenerator.exception.TooManyQuestionsRequestedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JavaQuestionServiceTest {

    private final JavaQuestionService out = new JavaQuestionService();

    public static Stream<Arguments> provideParamsForTestQuestionAndAnswer() {
        return Stream.of(
                Arguments.of(new Question("Что такое переменная", "Именованная область памяти")),
                Arguments.of(new Question("Что такое Java", "Язык программирования")),
                Arguments.of(new Question("Что такое объект", "Экземпляр класса"))
        );
    }

    @BeforeEach
    void setUp() {
        out.getAll().clear();
    }

    @Test
    void add_ShouldAddQuestionToCollection() {
        String questionText = "Что такое переменная";
        String answerText = "Именованная область памяти";
        int initialSize = out.getAll().size();

        out.add(questionText, answerText);
        Collection<Question> actual = out.getAll();

        assertThat(actual).contains(new Question(questionText, answerText))
                .hasSize(initialSize + 1);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestQuestionAndAnswer")
    void addQuestion_ShouldAddQuestionToCollection(Question question) {
        int initialSize = out.getAll().size();

        out.add(question);
        Collection<Question> actual = out.getAll();

        assertThat(actual).contains(question)
                .hasSize(initialSize + 1);
    }

    @Test
    void addQuestionAndAnswer_ShouldExceptionsWhenNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            out.add(null, null);
        });
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestQuestionAndAnswer")
    void getQuestionAndAnswer_ShouldReturnCorrectAll(Question question) {
        out.add(question);

        Collection<Question> actual = out.getAll();

        assertThat(actual).hasSize(1)
                .containsExactlyInAnyOrder(question);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestQuestionAndAnswer")
    void removeQuestionAndAnswerShouldRemoveExistingQuestion(Question question) {
        out.add(question);

        out.remove(question);
        Collection<Question> actual = out.getAll();

        assertThat(actual).doesNotContain(question);
    }

    @Test
    void getRandomQuestions_ShouldThrowIllegalArgumentExceptionAmountOfNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> out.getRandomQuestions(-1));
    }

    @Test
    void getQuestions_ShouldThrowExceptionWhenAmountIsZero() {
        assertThrows(IllegalArgumentException.class, () -> out.getRandomQuestions(0));
    }

    @Test
    void shouldThrowTooManyQuestionsRequestedException_WhenRequestedMoreThanAvailable() {
        out.add("Что такое объект", "Экземпляр класса");
        assertThrows(TooManyQuestionsRequestedException.class, () -> out.getRandomQuestions(2));
    }

}