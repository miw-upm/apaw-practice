package es.upm.miw.apaw.adapters.out.survey.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SurveyRepository extends JpaRepository<SurveyEntity, UUID> {
    boolean existsBySurveyQuestionsId(UUID id);
}
