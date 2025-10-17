package es.upm.miw.apaw.domain.models.winery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    private Integer score;
    private String comment;
    private Boolean recommended;
}
