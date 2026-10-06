package es.upm.miw.apaw.adapters.out.survey.postgres;

import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;
import es.upm.miw.apaw.domain.ports.out.survey.SurveyQuestionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SurveyQuestionAdapter implements SurveyQuestionGateway {
    private final SurveyQuestionRepository surveyQuestionRepository;

    @Override
    public SurveyQuestion create(SurveyQuestion surveyQuestion) {
        return this.surveyQuestionRepository
                .save(new SurveyQuestionEntity(surveyQuestion))
                .toDomain();
    }

    @Override
    public Optional<SurveyQuestion> read(UUID id) {
        return this.surveyQuestionRepository.findById(id)
                .map(SurveyQuestionEntity::toDomain);
    }

    @Override
    public SurveyQuestion update(SurveyQuestion surveyQuestion) {
        return this.surveyQuestionRepository
                .save(new SurveyQuestionEntity(surveyQuestion))
                .toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.surveyQuestionRepository.deleteById(id);
    }
}
