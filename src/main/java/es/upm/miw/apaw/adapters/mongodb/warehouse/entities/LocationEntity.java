package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import es.upm.miw.apaw.domain.models.warehouse.Location;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document

public class LocationEntity {

    @Id
    private UUID id;

    private Integer currentStock;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String position;

    private LocalDateTime lastUpdateDate;

    @DBRef
    private List<ProductItemEntity> productItemEntities;

    private Boolean availability;


    public LocationEntity(Location location) {
        BeanUtils.copyProperties(location, this, "productItems");
        this.productItemEntities = location.getProductItems() == null ? null :
                location.getProductItems().stream()
                        .map(ProductItemEntity::new)
                        .toList();
        this.id = UUID.randomUUID();
    }

    public Location toLocation() {
        return Location.builder()
                .currentStock(this.currentStock)
                .position(this.position)
                .lastUpdateDate(this.lastUpdateDate)
                .availability(this.availability)
                .productItems(
                        this.productItemEntities == null
                                ? new ArrayList<>()
                                : this.productItemEntities.stream()
                                .map(ProductItemEntity::toProductItem)
                                .toList()
                )
                .build();
    }

}