package nyumaguzhin.questionnaire.controller;

import lombok.AllArgsConstructor;
import nyumaguzhin.questionnaire.model.Question;
import nyumaguzhin.questionnaire.model.ResultOption;
import nyumaguzhin.questionnaire.model.ui.UiQuestion;
import nyumaguzhin.questionnaire.model.ui.UserAnswer;
import nyumaguzhin.questionnaire.service.QuestionnarieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionnaireController {
    private QuestionnarieService questionnarieService;

    @CrossOrigin
    @GetMapping("/questions")
    public Iterable<UiQuestion> getQuestions() {
        return questionnarieService.getQuestions();
    }

    @CrossOrigin
    @PostMapping("/result")
    public Iterable<ResultOption> postResult(@RequestBody List<UserAnswer> answers) {
        return questionnarieService.postResult(answers);
    }

}
