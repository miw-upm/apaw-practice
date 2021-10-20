package es.upm.miw.apaw_practice.domain.models.car_hire;

import java.math.BigDecimal;

public interface VehicleBuilders {

    interface VinNumber {
        DailyCost vinNumber(String vinNumber);
    }

    interface DailyCost {
        KilometersAmount dailyCost(BigDecimal dailyCost);
    }

    interface KilometersAmount {
        GoodCondition kilometersAmount(Integer kilometersAmount);
    }

    interface GoodCondition {
        VehicleBuild goodCondition(Boolean goodCondition);
    }

    interface VehicleBuild {
        Vehicle build();
    }
}
