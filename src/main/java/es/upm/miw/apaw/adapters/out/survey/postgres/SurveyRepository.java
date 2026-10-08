package es.upm.miw.apaw.adapters.out.survey.postgres;

import es.upm.miw.apaw.domain.model.survey.SurveyUserLanguageReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SurveyRepository extends JpaRepository<SurveyEntity, UUID> {
    boolean existsBySurveyQuestionsId(UUID id);

    boolean existsByTitle(String title);

    @Query("""
            select new es.upm.miw.apaw.domain.model.survey.SurveyUserLanguageReport(
                survey.userId,
                survey.language,
                count(distinct survey.id),
                count(question.id)
            )
            from SurveyEntity survey
            join survey.surveyQuestions question
            group by survey.userId, survey.language
            order by count(question.id) desc
            """)
    List<SurveyUserLanguageReport> findSurveyUserLanguageReport();
}
