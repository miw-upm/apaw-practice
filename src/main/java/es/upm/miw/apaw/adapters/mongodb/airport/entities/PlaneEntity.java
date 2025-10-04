package es.upm.miw.apaw.adapters.mongodb.airport.entities;

import es.upm.miw.apaw.domain.models.airport.Plane;
import es.upm.miw.apaw.domain.models.shop.Article;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class PlaneEntity {
    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String registrationNumber;
    private String model;
    private Integer seatCount;
    private LocalDateTime createdAt;
    private String manufacturer;

    public PlaneEntity(Plane plane) {
        BeanUtils.copyProperties(plane, this);
        this.id = UUID.randomUUID();
    }

    public Plane toPlane() {
        Plane plane = new Plane();
        BeanUtils.copyProperties(this, plane);
        return plane;
    }
}
