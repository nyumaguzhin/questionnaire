package nyumaguzhin.questionnaire.model.ui;

import lombok.Data;
import nyumaguzhin.questionnaire.model.Answer;
import nyumaguzhin.questionnaire.model.Question;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UiQuestion {
    private String id;
    private String questionText;
    private List<UiAnswer> options;

    public UiQuestion(Question question) {
        this.id = question.getId();
        this.questionText = question.getQuestionText();
        this.options = question.getOptions().stream().map(answer -> new UiAnswer(answer)).collect(Collectors.toList());
    }
}
