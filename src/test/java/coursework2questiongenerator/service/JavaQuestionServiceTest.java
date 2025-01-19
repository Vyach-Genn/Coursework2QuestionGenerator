package coursework2questiongenerator.service;

import coursework2questiongenerator.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private final JavaQuestionService out = new JavaQuestionService();

    @BeforeEach
    void setUp() {
        out.clearQuestions();
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestQuestionAndAnswer")
    void addQuestionAndAnswer_ShouldAddQuestionToCollection(String questionText, String answerText) {
        int initialSize = out.getQuestionAndAnswer().size();
        out.addQuestionAndAnswer(questionText, answerText);
        Set<Question> actual = out.getQuestionAndAnswer();

        assertThat(actual).contains(new Question(questionText, answerText))
                .hasSize(initialSize + 1);

    }

    @Test
    void addQuestionAndAnswer_ShouldExceptionsWhenQuestionNullAndAnswerNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            out.addQuestionAndAnswer(null, null);
        });
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestQuestionAndAnswer")
    void getQuestionAndAnswer_ShouldReturnCorrectQuestionAndAnswer(String questionText, String answerText) {
        out.addQuestionAndAnswer(questionText, answerText);
        Set<Question> actual = out.getQuestionAndAnswer();
        assertThat(actual).hasSize(1)
                .containsExactlyInAnyOrder(new Question(questionText, answerText));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestQuestionAndAnswer")
    void removeQuestionAndAnswerShouldRemoveExistingQuestion(String questionText, String answerText) {
        out.addQuestionAndAnswer(questionText, answerText);
        out.removeQuestionAndAnswer(questionText, answerText);
        Set<Question> actual = out.getQuestionAndAnswer();
        assertThat(actual).extracting(Question::getQuestion)
                .doesNotContain(questionText, answerText);
        assertThat(actual).isEmpty();

    }

    @Test
    void removeQuestionAndAnswer_ShouldExceptionsWhenQuestionNullAndAnswerNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            out.removeQuestionAndAnswer(null, null);
        });
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestQuestionAndAnswer")
    void removeQuestionAndAnswer_ShouldExceptionsWhenQuestionAndAnswerContains(String questionText, String answerText) {
        assertThat(out.getQuestionAndAnswer())
                .doesNotContain(new Question(questionText, answerText));

        assertThrows(NoSuchElementException.class, () -> {
            out.removeQuestionAndAnswer(questionText, answerText);
        });
    }

    @Test
    void getRandomQuestions_ShouldReturnCorrectAmountOfQuestions() {
        out.addQuestionAndAnswer("Что такое переменная", "Именованная область памяти");
        out.addQuestionAndAnswer("Что такое Java", "Язык программирования");
        out.addQuestionAndAnswer("Что такое объект", "Экземпляр класса");

        Set<Question> randomQuestions = out.getRandomQuestions(2);
        assertThat(randomQuestions).hasSize(2);
    }

    @Test
    void getRandomQuestions_ShouldThrowIllegalArgumentExceptionWhenTheNumberOfQuestionsRaisedIsGreaterThanTheExistingOnes() {
        out.addQuestionAndAnswer("Что такое объект", "Экземпляр класса");
        assertThrows(IllegalArgumentException.class, () -> out.getRandomQuestions(2));
    }

    public static Stream<Arguments> provideParamsForTestQuestionAndAnswer() {
        return Stream.of(
                Arguments.of("Что такое переменная", "Именованная область памяти"),
                Arguments.of("Что такое Java", "Язык программирования"),
                Arguments.of("Что такое объект", "Экземпляр класса")

        );
    }

}