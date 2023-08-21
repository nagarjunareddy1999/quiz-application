package in.arjun.my.quizservice.service;


import in.arjun.my.quizservice.Dao.QuizDao;
import in.arjun.my.quizservice.entity.*;
import in.arjun.my.quizservice.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(QuizDto quizDto) {
         List<Question> questions = quizInterface.getQuestionsForQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions()).getBody();
         Quiz quiz=new Quiz();
         quiz.setTitle(quizDto.getTitle());
         quiz.setQuestionIds(questions.stream().map(Question::getId).toList());
         quizDao.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
                Quiz quiz=quizDao.findById(id).get();
                List<Integer> questionIds=quiz.getQuestionIds();
               ResponseEntity<List<QuestionWrapper>> questions= quizInterface.getQuestionsFromId(questionIds);
               return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
               ResponseEntity<Integer> score=quizInterface.getScore(responses);
               return score;
    }
}
