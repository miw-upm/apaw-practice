package es.upm.miw.apaw.adapters.mongodb.football.entities;

import es.upm.miw.apaw.domain.models.football.Stadium;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StadiumEntity {

    @Id
    @EqualsAndHashCode.Include
    private Long stadiumId;
    private String officialName;
    private Integer capacity;
    private Boolean roof;

    public StadiumEntity(Stadium stadium) {
        BeanUtils.copyProperties(stadium, this);
    }

    public Stadium toStadium() {
        Stadium stadium = new Stadium();
        BeanUtils.copyProperties(this, stadium);
        return stadium;
    }
}
