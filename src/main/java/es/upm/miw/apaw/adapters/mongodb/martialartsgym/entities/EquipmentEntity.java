package es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities;

import es.upm.miw.apaw.domain.models.martialartsgym.Equipment;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class EquipmentEntity {

    @Id
    private Integer barCode;

    private String itemLabel;
    private BigDecimal unitCost;

    @DBRef
    private DojoEntity dojo;

    public Equipment toEquipment() {
        return Equipment.builder()
                .barCode(this.barCode)
                .itemLabel(this.itemLabel)
                .unitCost(this.unitCost)
                .build();
    }
}
