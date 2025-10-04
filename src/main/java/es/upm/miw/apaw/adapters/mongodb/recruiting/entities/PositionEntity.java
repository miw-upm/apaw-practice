package es.upm.miw.apaw.adapters.mongodb.recruiting.entities;

import lombok.*;
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
}
