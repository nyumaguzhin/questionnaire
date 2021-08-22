package nyumaguzhin.questionnaire.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import nyumaguzhin.questionnaire.model.Answer;
import nyumaguzhin.questionnaire.model.Question;
import nyumaguzhin.questionnaire.model.ResultOption;
import nyumaguzhin.questionnaire.model.ui.UiQuestion;
import nyumaguzhin.questionnaire.model.ui.UserAnswer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionnarieService {

    @Value("classpath:questions.json")
    Resource questionsFile;


    private List<Question> questions;

    @PostConstruct
    public void init() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            questions = Arrays.asList(objectMapper.readValue(questionsFile.getFile(), Question[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<UiQuestion> getQuestions() {
        return questions.stream().map(question -> new UiQuestion(question)).collect(Collectors.toList());
    }

    public Iterable<ResultOption> postResult(List<UserAnswer> answers) {
        HashMap<String, ResultOption> result = new HashMap<String, ResultOption>();
        int totalScores = 0;
        for (UserAnswer userAnswer : answers) {
            Question question = questions.stream().filter(q -> q.getId().equals(userAnswer.getQuestionId())).findFirst().orElse(null);
            if (question != null) {
                Answer answer = question.getOptions().stream().filter(o -> o.getId().equals(userAnswer.getAnswerId())).findFirst().orElse(null);
                if (answer != null && answer.getResults() != null) {
                    for (ResultOption currentScore : answer.getResults()) {
                        if (!result.containsKey(currentScore.getId())) {
                            ResultOption newResult = new ResultOption(currentScore.getId(), currentScore.getDescription(), currentScore.getScore());
                            result.put(currentScore.getId(), newResult);
                        } else {
                            ResultOption sumScore = result.get(currentScore.getId());
                            sumScore.setScore(sumScore.getScore() + currentScore.getScore());
                        }
                        totalScores += currentScore.getScore();
                    }
                }
            }
        }

        //convert to percentage
        int finalTotalScores = totalScores;
        result.forEach((key, score) -> score.setScore(score.getScore()*100/ finalTotalScores));

        return result.values();
    }


}
