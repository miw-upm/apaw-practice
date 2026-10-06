package es.upm.miw.apaw.domain.services.survey;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;
import es.upm.miw.apaw.domain.ports.out.survey.SurveyQuestionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SurveyQuestionService {
    private final SurveyQuestionGateway surveyQuestionGateway;

    public SurveyQuestion create(SurveyQuestion surveyQuestion) {
        surveyQuestion.doDefault();
        return this.surveyQuestionGateway.create(surveyQuestion);
    }

    public SurveyQuestion read(UUID id) {
        return this.surveyQuestionGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Survey question id not found: " + id));
    }
}
