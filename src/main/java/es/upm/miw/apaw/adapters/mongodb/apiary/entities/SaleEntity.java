package es.upm.miw.apaw.adapters.mongodb.apiary.entities;

import es.upm.miw.apaw.adapters.mongodb.shop.entities.ArticleEntity;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.apiary.Sale;

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
public class SaleEntity {
    @Id
    private UUID id;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private Integer idSale;

    private Integer paymentForm;
    private String shippingAddress;
    private BigDecimal amount;

    @DBRef
    private List<ProductEntity> productEntities;

    private UUID userId;

    public SaleEntity(Sale sale) {
        BeanUtils.copyProperties(sale, this, "client");
        this.id = UUID.randomUUID();
        if (sale.getClient() != null) {
            this.userId = sale.getClient().getId();
        }
    }

    public Sale toSale() {
        Sale sale = new Sale();
        BeanUtils.copyProperties(this, sale, "userId");
        if (this.userId != null) {
            sale.setClient(UserDto.builder().id(this.userId).build());
        }
        return sale;
    }
}
