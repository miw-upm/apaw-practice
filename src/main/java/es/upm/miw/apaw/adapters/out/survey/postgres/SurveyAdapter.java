package es.upm.miw.apaw.adapters.out.survey.postgres;

import es.upm.miw.apaw.domain.model.survey.Survey;
import es.upm.miw.apaw.domain.ports.out.survey.SurveyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SurveyAdapter implements SurveyGateway {
    private final SurveyRepository surveyRepository;
    private final SurveyQuestionRepository surveyQuestionRepository;

    @Override
    @Transactional
    public Survey create(Survey survey) {
        SurveyEntity surveyEntity = new SurveyEntity(survey);
        List<SurveyQuestionEntity> surveyQuestionEntities = survey.getSurveyQuestions().stream()
                .map(surveyQuestion -> this.surveyQuestionRepository.getReferenceById(surveyQuestion.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
        surveyEntity.setSurveyQuestions(surveyQuestionEntities);
        this.surveyRepository.save(surveyEntity);
        return survey;
    }

    @Override
    public boolean existsByTitle(String title) {
        return this.surveyRepository.existsByTitle(title);
    }
}
