package es.upm.miw.apaw.adapters.mongodb.clothingstore.entities;

import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "garments")
public class GarmentEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private String size;
    private BigDecimal price;
    private Boolean onSale;

    public GarmentEntity(Garment garment) {
        BeanUtils.copyProperties(garment, this);
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    public Garment toGarment() {
        Garment garment = new Garment();
        BeanUtils.copyProperties(this, garment);
        return garment;
    }

    public void fromGarment(Garment garment) {
        BeanUtils.copyProperties(garment, this);
    }
}
