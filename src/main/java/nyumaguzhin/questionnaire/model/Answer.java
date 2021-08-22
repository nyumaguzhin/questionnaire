package nyumaguzhin.questionnaire.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.List;

@Data
public class Answer {
    private String id;
    private String answerText;
    private List<ResultOption> results;
}
