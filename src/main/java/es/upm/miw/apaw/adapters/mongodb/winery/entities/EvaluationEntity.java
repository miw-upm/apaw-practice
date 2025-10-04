package es.upm.miw.apaw.adapters.mongodb.winery.entities;

import es.upm.miw.apaw.domain.models.winery.Evaluation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationEntity {
    private Long idEvaluation;
    private Integer score;
    private String comment;

    public EvaluationEntity(Evaluation evaluation) {
        BeanUtils.copyProperties(evaluation, this);
    }

    public Evaluation toEvaluation(){
        Evaluation evaluation = new Evaluation();
        BeanUtils.copyProperties(this, evaluation);
        return evaluation;
    }

}
