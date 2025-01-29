package coursework.questiongenerator.service;

import coursework.questiongenerator.domain.Question;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class JavaQuestionService implements QuestionService {

    Random random = new Random();
    Set<Question> questions = new HashSet<>();
    private final Set<Question> usedQuestions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        if ((question == null || answer == null) || (question.isEmpty() || answer.isEmpty())) {
            throw new IllegalArgumentException("Вопрос или ответ не могут быть null");
        }
        questions.add(new Question(question, answer));
        return new Question(question, answer);

    }

    @Override
    public Question add(Question question) {
        if (question.isEmpty()) {
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
    public Question getRandomQuestions() {
        if (questions.isEmpty()) {
            throw new IllegalStateException("Нет доступных вопросов");
        }
        List<Question> questionList = new ArrayList<>(questions);
        int randomIndex = random.nextInt(questionList.size());
        Question randomQuestion = questionList.get(randomIndex);
        usedQuestions.add(randomQuestion);
        return randomQuestion;
    }
}
