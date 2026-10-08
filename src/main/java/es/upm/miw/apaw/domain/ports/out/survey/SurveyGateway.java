package es.upm.miw.apaw.domain.ports.out.survey;

import es.upm.miw.apaw.domain.model.survey.Survey;

public interface SurveyGateway {
    Survey create(Survey survey);

    boolean existsByTitle(String title);
}
