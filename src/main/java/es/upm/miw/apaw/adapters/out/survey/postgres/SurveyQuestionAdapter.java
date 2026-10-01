package es.upm.miw.apaw.adapters.out.survey.postgres;

import es.upm.miw.apaw.domain.ports.out.survey.SurveyQuestionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SurveyQuestionAdapter implements SurveyQuestionGateway {
    private final SurveyQuestionRepository surveyQuestionRepository;
}
