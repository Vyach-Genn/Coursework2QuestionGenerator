package coursework.questiongenerator.controller;


import coursework.questiongenerator.domain.Question;
import coursework.questiongenerator.service.ExaminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/get")

public class ExamController {

   private final ExaminerService examService;
@Autowired
    public ExamController(ExaminerService examService) {
        this.examService = examService;
    }

    @GetMapping("/{amount}")
    public Collection<Question> getQuestions(@PathVariable int amount) {
        return examService.getQuestions(amount);
    }
}
