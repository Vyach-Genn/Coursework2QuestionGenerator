package coursework2questiongenerator.controller;


import coursework2questiongenerator.domain.Question;
import coursework2questiongenerator.service.ExaminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/exam/get")

public class ExamController {

   private final ExaminerService examService;
@Autowired
    public ExamController(ExaminerService examService) {
        this.examService = examService;
    }

    @GetMapping("/{amount}")
    public Set<Question> getSoAmountQuestionsAndAnswers(@PathVariable int amount) {
        return examService.getSoAmountQuestionsAndAnswers(amount);
    }
}
