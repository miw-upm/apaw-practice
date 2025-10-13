package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import es.upm.miw.apaw.domain.models.warehouse.Location;
import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
    @NotNull
    private Integer currentStock;
    @NotBlank
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String position;
    private LocalDateTime lastUpdateDate;
    @DBRef
    private List<ProductItemEntity> productItemEntities;
    @NotNull
    private Boolean availability;


    public Location toLocation() {
        List<ProductItem> productItems = this.productItemEntities.stream()
                .map(ProductItemEntity::toProductItem)
                .toList();
        return new Location(currentStock,position,lastUpdateDate,productItems,availability);
    }

}
