package es.upm.miw.apaw.adapters.mongodb.winery.entities;

import es.upm.miw.apaw.domain.models.winery.Wine;
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
@Document
public class WineEntity {
    @EqualsAndHashCode.Include
    @Id
    private UUID id;
    private String name;
    private Integer year;
    private Double alcoholPercentage;
    private BigDecimal price;

    public WineEntity(Wine wine){
        BeanUtils.copyProperties(wine, this);
    }

    public Wine toWine(){
        Wine wine = new Wine();
        BeanUtils.copyProperties(this, wine);
        return wine;
    }
}
