package es.upm.miw.apaw.adapters.mongodb.metro.entities;

import es.upm.miw.apaw.domain.models.metro.Zone;
import lombok.*;
import org.springframework.beans.BeanUtils;
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

    public ZoneEntity(Zone zone) {
        BeanUtils.copyProperties(zone, this);
    }

    public Zone toZone() {
        Zone zone = new Zone();
        BeanUtils.copyProperties(this, zone);
        return zone;
    }
}
