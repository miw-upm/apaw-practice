package es.upm.miw.apaw.adapters.mongodb.winery.entities;

import es.upm.miw.apaw.domain.models.winery.Evaluation;
import es.upm.miw.apaw.domain.models.winery.TastingSession;
import es.upm.miw.apaw.domain.models.winery.Wine;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class TastingSessionEntity {
    @EqualsAndHashCode.Include
    @Id
    private Long idSession;
    private LocalDate date;
    private Integer capacity;
    private String location;
    @DBRef
    private List<WineEntity> wineEntities;
    private List<EvaluationEntity> evaluationEntities;

    public TastingSessionEntity(TastingSession tastingSession){
        BeanUtils.copyProperties(tastingSession, this, "evaluationEntities");
        this.evaluationEntities = tastingSession.getEvaluations().stream()
                .map(EvaluationEntity::new)
                .toList();
    }

    public TastingSession toTastingSession(){
        TastingSession tastingSession = new TastingSession();
        BeanUtils.copyProperties(this, tastingSession, "evaluationEntities");
        List<Evaluation> evaluations = this.evaluationEntities.stream()
                .map(EvaluationEntity::toEvaluation)
                .toList();
        tastingSession.setEvaluations(evaluations);
        List<Wine> wines = this.wineEntities.stream()
                .map(WineEntity::toWine)
        .toList();

        tastingSession.setWines(wines);
        return tastingSession;
    }

}
