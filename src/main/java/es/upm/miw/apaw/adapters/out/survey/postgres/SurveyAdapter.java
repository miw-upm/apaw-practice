package es.upm.miw.apaw.adapters.out.survey.postgres;

import es.upm.miw.apaw.domain.ports.out.survey.SurveyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SurveyAdapter implements SurveyGateway {
    private final SurveyRepository surveyRepository;
}
