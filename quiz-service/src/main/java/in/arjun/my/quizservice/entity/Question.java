package in.arjun.my.quizservice.entity;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultylevel;
    private String category;

}
