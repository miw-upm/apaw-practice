package es.upm.miw.apaw.domain.ports.out.survey;

import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;

import java.util.Optional;
import java.util.UUID;

public interface SurveyQuestionGateway {
    SurveyQuestion create(SurveyQuestion surveyQuestion);

    Optional<SurveyQuestion> read(UUID id);

    SurveyQuestion update(SurveyQuestion surveyQuestion);
}
