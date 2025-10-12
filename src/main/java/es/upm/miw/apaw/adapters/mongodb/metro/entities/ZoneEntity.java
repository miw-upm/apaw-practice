package es.upm.miw.apaw.adapters.mongodb.metro.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class ZoneEntity {

    @Id
    private String type;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private BigDecimal ticketPrice;
}
