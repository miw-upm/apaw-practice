package es.upm.miw.apaw.adapters.mongodb.vehicle.entities;

import es.upm.miw.apaw.domain.models.vehicle.Extra;
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
public class ExtraEntity {
    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String category;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String description;
    private BigDecimal price;
}
