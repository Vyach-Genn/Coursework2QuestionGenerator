package coursework.questiongenerator.service;

import coursework.questiongenerator.domain.Question;

import java.util.Collection;
import java.util.Set;

public interface QuestionService {


    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Set<Question> getRandomQuestions(int amount);
}
