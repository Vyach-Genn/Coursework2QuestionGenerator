package coursework.questiongenerator.service;

import coursework.questiongenerator.domain.Question;

import java.util.Collection;

public interface ExaminerService {


    Collection<Question> getQuestions(int amount);
}
