package coursework2.questiongenerator.service;

import coursework2.questiongenerator.domain.Question;
import coursework2.questiongenerator.exception.TooManyQuestionsRequestedException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Service
public class JavaQuestionService implements QuestionService {

    Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        if (question == null && answer == null) {
            throw new IllegalArgumentException("Вопрос и ответ не могут быть null");
        }
        questions.add(new Question(question, answer));
        return new Question(question, answer);

    }

    @Override
    public Question add(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Вопрос и ответ не могут быть null");
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return new HashSet<>(questions);
    }

    @Override
    public Set<Question> getRandomQuestions(int amount) {
        if (amount > questions.size()) {
            throw new TooManyQuestionsRequestedException("Количество вопросов не может быть больше общего числа вопросов");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Количество вопросов не может быть отрицательным, или нулевым");
        }
        return new HashSet<>(questions);

    }
}
