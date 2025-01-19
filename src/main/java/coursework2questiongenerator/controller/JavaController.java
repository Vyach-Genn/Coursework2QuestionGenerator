package coursework2questiongenerator.controller;


import coursework2questiongenerator.domain.Question;
import coursework2questiongenerator.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/exam/java")
public class JavaController {

    private final QuestionService questionService;

    @Autowired
    public JavaController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public void addQuestionAndAnswer(@RequestParam("question") String question,
                                     @RequestParam("answer") String answer) {
        questionService.addQuestionAndAnswer(question, answer);
    }

    @DeleteMapping("/remove")
    public void removeQuestion(@RequestParam("question") String question,
                               @RequestParam("answer") String answer) {
        questionService.removeQuestionAndAnswer(question, answer);
    }

    @GetMapping
    public Set<Question> getQuestionAndAnswer() {
        return questionService.getQuestionAndAnswer();
    }

}
