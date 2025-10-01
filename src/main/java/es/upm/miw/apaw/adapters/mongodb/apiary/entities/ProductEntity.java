package es.upm.miw.apaw.adapters.mongodb.apiary.entities;

import es.upm.miw.apaw.domain.models.apiary.Product;

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
public class ProductEntity {
    @Id
    private UUID id;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String barcode;

    private String product;
    private BigDecimal price;

    @DBRef
    private List<SaleEntity> saleEntities;

    public ProductEntity(Product product) {
        BeanUtils.copyProperties(product, this, "sales");
        this.id = UUID.randomUUID();
        if (product.getSales() != null) {
            this.saleEntities = product.getSales().stream()
                    .map(SaleEntity::new)
                    .toList();
        }
    }

    public Product toProduct() {
        Product product = new Product();
        BeanUtils.copyProperties(this, product, "saleEntities");
        if (this.saleEntities != null) {
            product.setSales(this.saleEntities.stream()
                    .map(SaleEntity::toSale)
                    .toList());
        }
        return product;
    }
}
