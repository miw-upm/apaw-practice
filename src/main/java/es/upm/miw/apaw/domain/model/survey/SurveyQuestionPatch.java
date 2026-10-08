package es.upm.miw.apaw.domain.model.survey;

import java.util.List;

public record SurveyQuestionPatch(
        String text,
        SurveyQuestionType surveyQuestionType,
        Boolean required,
        Integer maxLength,
        List<String> options
) {
}
