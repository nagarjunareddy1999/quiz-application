package in.arjun.my.questionservice.controller;





import in.arjun.my.questionservice.entity.Question;
import in.arjun.my.questionservice.entity.QuestionWrapper;
import in.arjun.my.questionservice.entity.Response;
import in.arjun.my.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){

        return questionService.getAllQuestions();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
     return questionService.getQuestionsByCategory(category);
    }
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);

    }
    @GetMapping("generate")
    public ResponseEntity<List<Question>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestions){
         return questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }

    @PostMapping("getQuestions")
        public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds){
        return questionService.getQuestionsFromId(questionIds);
        }

        @PostMapping("getScore")
          public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
           return questionService.getScore(responses);
        }

}
