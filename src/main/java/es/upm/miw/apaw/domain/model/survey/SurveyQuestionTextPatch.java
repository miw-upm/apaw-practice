package es.upm.miw.apaw.domain.model.survey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SurveyQuestionTextPatch(@NotNull UUID id, @NotBlank String text) {
}
