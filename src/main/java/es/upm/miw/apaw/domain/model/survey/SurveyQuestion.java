package es.upm.miw.apaw.domain.model.survey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SurveyQuestion {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String text;

    private SurveyQuestionType type;

    private Boolean required;

    private Integer maxLength;

    @NotEmpty
    private List<String> options;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.type == null) {
            this.type = SurveyQuestionType.TEXT;
        }
        if (this.required == null) {
            this.required = true;
        }
    }
}
