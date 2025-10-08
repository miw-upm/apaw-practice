package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import es.upm.miw.apaw.domain.models.warehouse.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document

public class LocationEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    private Integer currentStock;

    @NotBlank
    private String position;

    private LocalDateTime lastUpdateDate;

    @NotNull
    private Boolean availability;

    public LocationEntity(Location location) {
        BeanUtils.copyProperties(location, this);
        this.id = UUID.randomUUID();
    }

    public Location toLocation() {
        Location location = new Location();
        BeanUtils.copyProperties(this, location);
        return location;
    }

}
