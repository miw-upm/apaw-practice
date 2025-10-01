package es.upm.miw.apaw.adapters.mongodb.airport.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class BoardingGateEntity {
    @Id
    private UUID id;
    private String gateNumber;
    private String terminal;
    private Boolean opened;
}
