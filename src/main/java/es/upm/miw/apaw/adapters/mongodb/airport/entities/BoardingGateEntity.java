package es.upm.miw.apaw.adapters.mongodb.airport.entities;

import es.upm.miw.apaw.domain.models.airport.BoardingGate;
import lombok.*;
import org.springframework.beans.BeanUtils;
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
    private String number;
    private String terminal;
    private Boolean opened;

    public BoardingGate toBoardingGate() {
        BoardingGate boardingGate = new BoardingGate();
        BeanUtils.copyProperties(this, boardingGate);
        return boardingGate;
    }
}
