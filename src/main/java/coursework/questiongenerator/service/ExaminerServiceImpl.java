package coursework.questiongenerator.service;

import coursework.questiongenerator.domain.Question;
import coursework.questiongenerator.exception.TooManyQuestionsRequestedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    // Random обязателен по программе. но абсолютно не нужный
    private final Random random = new Random();
    private final QuestionService questionService;

    @Autowired
    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    // В данной ситуации мы можем использовать либо Random напрямую в методе getQuestions,
    // либо метод getRandomQuestions, их совместное использование в одном методе не имеет смысла
    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> allQuestions = questionService.getAll();
        validateAmount(amount, allQuestions.size());
        Set<Question> randomQuestions = new HashSet<>();
        while (randomQuestions.size() < amount) {
            Question randomQuestion = questionService.getRandomQuestions();
            randomQuestions.add(randomQuestion);
        }
        return randomQuestions;
    }

    public void validateAmount(int amount, int maxSize) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Количество вопросов не может быть отрицательным или нулевым");
        }
        if (amount > maxSize) {
            throw new TooManyQuestionsRequestedException("Количество вопросов не может быть больше общего числа вопросов");
        }
    }
}
