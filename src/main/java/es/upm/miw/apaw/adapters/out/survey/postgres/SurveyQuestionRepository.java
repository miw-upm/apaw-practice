package es.upm.miw.apaw.adapters.out.survey.postgres;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestionEntity, UUID> {
    @Override
    @EntityGraph(attributePaths = "options")
    Optional<SurveyQuestionEntity> findById(UUID id);

    @EntityGraph(attributePaths = "options")
    List<SurveyQuestionEntity> findAllByOrderByTextAsc();
}
