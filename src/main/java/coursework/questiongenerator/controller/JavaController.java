package coursework.questiongenerator.controller;


import coursework.questiongenerator.domain.Question;
import coursework.questiongenerator.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaController {

    private final QuestionService service;

    @Autowired
    public JavaController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam("question") String question,
                                     @RequestParam("answer") String answer) {
        return service.add(question, answer);
    }

    //С учетом того что у нас по заданию 2 метода add просто строковой и объектный
    @GetMapping("/add/object")
    public Question addQuestionObject(@RequestParam("question") String question,
                                @RequestParam("answer") String answer) {
        return service.add(new Question(question, answer));
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam("question") String question,
                               @RequestParam("answer") String answer) {
        Question questionToRemove = new Question(question, answer);
        return service.remove(questionToRemove);
    }

    @GetMapping
    public Collection<Question> getQuestion() {
        return service.getAll();
    }

}
