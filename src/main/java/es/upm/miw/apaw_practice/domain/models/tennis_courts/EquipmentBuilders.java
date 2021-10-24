package es.upm.miw.apaw_practice.domain.models.tennis_courts;

import java.math.BigDecimal;

public interface EquipmentBuilders {

    interface Type{
        Quantity type(String type);
    }

    interface Quantity{
        PricePerUnit quantity(Integer quantity);
    }

    interface PricePerUnit{
        EquipmentBuilders pricePerUnit(BigDecimal pricePerUnit);
    }

    Equipment build();
}
