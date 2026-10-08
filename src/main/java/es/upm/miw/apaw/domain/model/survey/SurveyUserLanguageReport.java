package es.upm.miw.apaw.domain.model.survey;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyUserLanguageReport {
    private UserSnapshot userSnapshot;
    private String language;
    private long surveyCount;
    private long surveyQuestionCount;

    public SurveyUserLanguageReport(
            UUID userId, String language, long surveyCount, long surveyQuestionCount) {
        this.userSnapshot = UserSnapshot.builder().id(userId).build();
        this.language = language;
        this.surveyCount = surveyCount;
        this.surveyQuestionCount = surveyQuestionCount;
    }
}
