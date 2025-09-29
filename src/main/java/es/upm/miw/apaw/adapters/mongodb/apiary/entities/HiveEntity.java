package es.upm.miw.apaw.adapters.mongodb.apiary.entities;

import es.upm.miw.apaw.domain.models.apiary.Hive;

import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document

public class HiveEntity {
    @Id
    private UUID id;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private Integer code;

    private String type;
    private Boolean queen;
    private LocalDate installationDate;

    @DBRef
    private ProductEntity productEntity;

    public HiveEntity(Hive hive) {
        BeanUtils.copyProperties(hive, this, "product");
        this.id = UUID.randomUUID();
        if (hive.getProduct() != null) {
            this.productEntity = new ProductEntity(hive.getProduct());
        }
    }

    public Hive toHive() {
        Hive hive = new Hive();
        BeanUtils.copyProperties(this, hive, "productEntity");
        if (this.productEntity != null) {
            hive.setProduct(this.productEntity.toProduct());
        }
        return hive;
    }
}
