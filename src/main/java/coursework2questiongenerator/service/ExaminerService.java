package coursework2questiongenerator.service;

import coursework2questiongenerator.domain.Question;

import java.util.Set;

public interface ExaminerService {


    Set<Question> getSoAmountQuestionsAndAnswers(int amount);
}
