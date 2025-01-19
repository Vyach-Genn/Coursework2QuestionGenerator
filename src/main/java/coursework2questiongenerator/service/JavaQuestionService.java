package coursework2questiongenerator.service;

import coursework2questiongenerator.domain.Question;
import coursework2questiongenerator.exception.TooManyQuestionsRequestedException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class JavaQuestionService implements QuestionService {

    Set<Question> questions = new HashSet<>();

    public JavaQuestionService() {

        questions.add(new Question("Что такое переменная", "Именованная область памяти"));
        questions.add(new Question("Что такое Java", "Язык программирования"));
        questions.add(new Question("Что такое метод", "Блок кода, который выполняет определённую задачу"));
        questions.add(new Question("Что такое полиморфизм", "Одно из основных свойств ООП"));
        questions.add(new Question("Что такое массив", "Структура данных"));
        questions.add(new Question("Что такое объект", "Экземпляр класса"));
        questions.add(new Question("Как работает Set", "Хранит уникальные элементы"));

    }

    @Override
    public void addQuestionAndAnswer(String question, String answer) {
        if (question != null && answer != null) {
            questions.add(new Question(question, answer));
        }else {
        throw new IllegalArgumentException("Вопрос и ответ не могут быть null");
        }

    }

    @Override
    public Set<Question> getQuestionAndAnswer() {
        return new HashSet<>(questions);
    }

    @Override
    public void removeQuestionAndAnswer(String question, String answer) {
        if (question == null || answer == null) {
            throw new IllegalArgumentException("Вопрос и ответ не могут быть null");
        }
        Question questionToRemove = new Question(question, answer);
        if (!questions.contains(questionToRemove)) {
            throw new NoSuchElementException("Вопрос с указанным текстом и ответом не найден");
        }
        questions.remove(questionToRemove);
    }

    @Override
    public Set<Question> getRandomQuestions(int amount) {
        if (amount > questions.size()) {
            throw new TooManyQuestionsRequestedException("Количество вопросов не может быть больше общего числа вопросов");
        }
        List<Question> questionsList = new ArrayList<>(questions);
        Collections.shuffle(questionsList);
        return new HashSet<>(questionsList.subList(0, amount));
    }

    public void clearQuestions() {
        questions.clear();
    }


}
