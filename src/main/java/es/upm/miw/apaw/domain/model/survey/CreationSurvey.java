package es.upm.miw.apaw.domain.model.survey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationSurvey {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String language;

    @NotEmpty
    private List<@NotNull UUID> surveyQuestionIds;

    @NotNull
    private UUID userId;
}
