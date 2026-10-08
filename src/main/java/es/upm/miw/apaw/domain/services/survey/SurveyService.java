package es.upm.miw.apaw.domain.services.survey;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.survey.CreationSurvey;
import es.upm.miw.apaw.domain.model.survey.Survey;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;
import es.upm.miw.apaw.domain.ports.out.survey.SurveyGateway;
import es.upm.miw.apaw.domain.ports.out.survey.SurveyQuestionGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyGateway surveyGateway;
    private final SurveyQuestionGateway surveyQuestionGateway;
    private final UserFinder userFinder;

    public Survey create(CreationSurvey creation) {
        if (this.surveyGateway.existsByTitle(creation.getTitle())) {
            throw new ConflictException("Survey title already exists: " + creation.getTitle());
        }
        Survey survey = new Survey();
        BeanUtils.copyProperties(creation, survey);
        survey.setSurveyQuestions(creation.getSurveyQuestionIds().stream()
                .map(this::readSurveyQuestion)
                .toList());
        survey.setUserSnapshot(this.userFinder.read(creation.getUserId()));
        survey.doDefault();
        return this.surveyGateway.create(survey);
    }

    private SurveyQuestion readSurveyQuestion(UUID id) {
        return this.surveyQuestionGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Survey question id not found: " + id));
    }
}
