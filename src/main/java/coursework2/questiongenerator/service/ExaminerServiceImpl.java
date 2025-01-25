package coursework2.questiongenerator.service;

import coursework2.questiongenerator.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final Random random = new Random();
    private final QuestionService questionService;

    @Autowired
    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<Question> questionList = new ArrayList<>(questionService.getRandomQuestions(amount));
        Set<Question> randomQuestions = new HashSet<>();
        while (randomQuestions.size() < amount) {
            int randomIndex = random.nextInt(questionList.size());
            randomQuestions.add(questionList.get(randomIndex));
        }
        return randomQuestions;
    }
}
