package es.upm.miw.apaw.domain.model.survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyFindCriteria {

    private String language;

    private Boolean submitted;

    private SurveyQuestionType surveyQuestionType;

    private String userCity;

    public boolean isAll() {
        return !this.hasLanguage() && !this.hasSubmitted()
                && !this.hasSurveyQuestionType() && !this.hasUserCity();
    }

    public boolean hasLanguage() {
        return this.language != null && !this.language.isBlank();
    }

    public boolean hasSubmitted() {
        return this.submitted != null;
    }

    public boolean hasSurveyQuestionType() {
        return this.surveyQuestionType != null;
    }

    public boolean hasUserCity() {
        return this.userCity != null && !this.userCity.isBlank();
    }
}
