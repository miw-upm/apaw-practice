package es.upm.miw.apaw.adapters.out.survey.postgres;

import es.upm.miw.apaw.domain.model.survey.Survey;
import es.upm.miw.apaw.domain.model.survey.SurveyUserLanguageReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Comparator;
import java.util.List;

import static es.upm.miw.apaw.config.seeders.SurveySeederForDev.SURVEY_0;
import static es.upm.miw.apaw.config.seeders.SurveySeederForDev.SURVEY_1;
import static es.upm.miw.apaw.config.seeders.SurveySeederForDev.SURVEY_2;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SurveyRepositoryIT {
    @Autowired
    private SurveyRepository surveyRepository;

    @Test
    void testFindUserLanguageReportSortedByQuestionCount() {
        List<SurveyUserLanguageReport> report = this.surveyRepository.findSurveyUserLanguageReport();

        assertThat(report).extracting(SurveyUserLanguageReport::getSurveyQuestionCount)
                .isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    void testFindUserLanguageReportAggregatesSeededSurveys() {
        List<SurveyUserLanguageReport> report = this.surveyRepository.findSurveyUserLanguageReport();

        this.assertSeededRow(report, SURVEY_0, 1, 2);
        this.assertSeededRow(report, SURVEY_1, 1, 1);
        this.assertSeededRow(report, SURVEY_2, 1, 3);
    }

    @Test
    void testFindUserLanguageReportOnlyCarriesUserId() {
        List<SurveyUserLanguageReport> report = this.surveyRepository.findSurveyUserLanguageReport();

        assertThat(report).isNotEmpty().allSatisfy(row -> {
            assertThat(row.getUserSnapshot().getId()).isNotNull();
            assertThat(row.getUserSnapshot().getMobile()).isNull();
        });
    }

    private void assertSeededRow(
            List<SurveyUserLanguageReport> report, Survey survey, long surveyCount, long surveyQuestionCount) {
        assertThat(report)
                .filteredOn(row -> row.getUserSnapshot().getId().equals(survey.getUserSnapshot().getId()))
                .singleElement()
                .satisfies(row -> {
                    assertThat(row.getLanguage()).isEqualTo(survey.getLanguage());
                    assertThat(row.getSurveyCount()).isEqualTo(surveyCount);
                    assertThat(row.getSurveyQuestionCount()).isEqualTo(surveyQuestionCount);
                });
    }
}