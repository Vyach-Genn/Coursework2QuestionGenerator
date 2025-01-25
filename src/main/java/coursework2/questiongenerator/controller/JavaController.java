package coursework2.questiongenerator.controller;


import coursework2.questiongenerator.domain.Question;
import coursework2.questiongenerator.service.QuestionService;
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
