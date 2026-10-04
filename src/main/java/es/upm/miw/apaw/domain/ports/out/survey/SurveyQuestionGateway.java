package es.upm.miw.apaw.domain.ports.out.survey;

import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;

public interface SurveyQuestionGateway {
    SurveyQuestion create(SurveyQuestion surveyQuestion);
}
