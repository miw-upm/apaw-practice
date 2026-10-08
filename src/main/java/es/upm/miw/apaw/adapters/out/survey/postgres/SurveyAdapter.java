package es.upm.miw.apaw.adapters.out.survey.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.survey.Survey;
import es.upm.miw.apaw.domain.model.survey.SurveyFindCriteria;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestionType;
import es.upm.miw.apaw.domain.model.survey.SurveyUserLanguageReport;
import es.upm.miw.apaw.domain.ports.out.survey.SurveyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    public List<Survey> find(SurveyFindCriteria criteria) {
        Specification<SurveyEntity> specification = this.buildSpecification(criteria);
        return this.surveyRepository.findAll(specification, Sort.by("title")).stream()
                .map(this::toDomainWithoutSurveyQuestions)
                .toList();
    }

    private Survey toDomainWithoutSurveyQuestions(SurveyEntity entity) {
        Survey survey = new Survey();
        BeanUtils.copyProperties(entity, survey, "surveyQuestions", "userId");
        survey.setUserSnapshot(UserSnapshot.builder().id(entity.getUserId()).build());
        return survey;
    }

    private Specification<SurveyEntity> buildSpecification(SurveyFindCriteria criteria) {
        Specification<SurveyEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria.hasLanguage()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("language"), criteria.getLanguage()));
        }
        if (criteria.hasSubmitted()) {
            specification = specification.and((root, query, builder) -> criteria.getSubmitted()
                    ? builder.isNotNull(root.get("submittedDate")) : builder.isNull(root.get("submittedDate")));
        }
        return this.addSurveyQuestionType(specification, criteria.getSurveyQuestionType());
    }

    private Specification<SurveyEntity> addSurveyQuestionType(
            Specification<SurveyEntity> specification, SurveyQuestionType surveyQuestionType) {
        if (surveyQuestionType == null) {
            return specification;
        }
        return specification.and((root, query, builder) -> {
            query.distinct(true);
            return builder.equal(root.join("surveyQuestions").get("surveyQuestionType"), surveyQuestionType);
        });
    }

    @Override
    public List<SurveyUserLanguageReport> findUserLanguageReport() {        return this.surveyRepository.findSurveyUserLanguageReport();
    }

    @Override
    public boolean existsByTitle(String title) {
        return this.surveyRepository.existsByTitle(title);
    }
}
