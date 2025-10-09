package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import es.upm.miw.apaw.domain.models.warehouse.Location;
import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document

public class ProductItemEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID        id;

    @Indexed(unique = true)
    private String      barcode;

    private String      appoint;
    private BigDecimal  cost;

    private String      unitOfMeasure;

    @DBRef
    private List<LocationEntity> locationEntities;

    public ProductItemEntity(ProductItem productItem) {
        BeanUtils.copyProperties(productItem, this, "locationEntities");
        this.id = (productItem.getId() != null) ? productItem.getId() : UUID.randomUUID();

        if (productItem.getLocations() != null) {
            this.locationEntities = productItem.getLocations().stream()
                    .map(LocationEntity::new)
                    .toList();
        }
    }

    public ProductItem toProductItem() {

        List<Location> locationList = (this.locationEntities != null)
                ? this.locationEntities.stream()
                .map(LocationEntity::toLocation)
                .toList()
                : List.of();

        return new ProductItem(
                this.id,
                this.barcode,
                this.appoint,
                this.cost,
                this.unitOfMeasure,
                locationList
        );
    }

}
