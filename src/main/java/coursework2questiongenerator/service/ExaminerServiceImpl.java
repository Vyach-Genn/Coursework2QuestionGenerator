package coursework2questiongenerator.service;

import coursework2questiongenerator.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    @Autowired
    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Set<Question> getSoAmountQuestionsAndAnswers(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Количество вопросов должно быть больше 0");
        }
        return questionService.getRandomQuestions(amount);
    }
}
