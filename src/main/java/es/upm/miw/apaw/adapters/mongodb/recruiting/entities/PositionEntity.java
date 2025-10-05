package es.upm.miw.apaw.adapters.mongodb.recruiting.entities;

import es.upm.miw.apaw.domain.models.recruiting.Position;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class PositionEntity {
    @Id
    private UUID id;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private int reference;

    private String name;
    private String description;
    private BigDecimal annualSalary;
    private BigDecimal bonusSalary;
    private Integer numVacancies;

    public PositionEntity(Position position) {
        BeanUtils.copyProperties(position, this);
        this.id = UUID.randomUUID();
    }

    public Position toPosition() {
        Position position = new Position();
        BeanUtils.copyProperties(this, position);
        return position;
    }
}
