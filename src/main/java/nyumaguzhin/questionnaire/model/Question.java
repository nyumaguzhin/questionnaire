package nyumaguzhin.questionnaire.model;


import lombok.Data;

import java.util.List;

@Data
public class Question {
    private String id;
    private String questionText;
    private List<Answer> options;
}
