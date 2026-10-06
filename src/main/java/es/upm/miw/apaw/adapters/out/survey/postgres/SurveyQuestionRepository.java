package es.upm.miw.apaw.adapters.out.survey.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestionEntity, UUID> {
    @Query("SELECT COUNT(s) > 0 FROM SurveyEntity s JOIN s.surveyQuestions sq WHERE sq.id = :id")
    boolean isReferencedInAnySurvey(@Param("id") UUID id);
}
