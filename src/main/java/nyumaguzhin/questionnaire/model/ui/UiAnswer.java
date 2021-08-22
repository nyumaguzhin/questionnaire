package nyumaguzhin.questionnaire.model.ui;

import lombok.Data;
import nyumaguzhin.questionnaire.model.Answer;

@Data
public class UiAnswer {
    private String id;
    private String answerText;

    public UiAnswer (Answer answer) {
      this.id = answer.getId();
      this.answerText = answer.getAnswerText();
    }
}
