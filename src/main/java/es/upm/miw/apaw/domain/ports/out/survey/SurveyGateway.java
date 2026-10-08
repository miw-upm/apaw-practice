package es.upm.miw.apaw.domain.ports.out.survey;

import es.upm.miw.apaw.domain.model.survey.Survey;
import es.upm.miw.apaw.domain.model.survey.SurveyUserLanguageReport;

import java.util.List;

public interface SurveyGateway {
    Survey create(Survey survey);

    List<SurveyUserLanguageReport> findUserLanguageReport();

    boolean existsByTitle(String title);
}
