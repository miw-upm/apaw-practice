package es.upm.miw.apaw.adapters.out.survey.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestionEntity, UUID> {
    List<SurveyQuestionEntity> findAllByOrderByTextAsc();
}
