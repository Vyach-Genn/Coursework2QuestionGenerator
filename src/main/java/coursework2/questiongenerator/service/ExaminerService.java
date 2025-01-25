package coursework2.questiongenerator.service;

import coursework2.questiongenerator.domain.Question;

import java.util.Collection;

public interface ExaminerService {


    Collection<Question> getQuestions(int amount);
}
