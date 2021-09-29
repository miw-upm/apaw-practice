package es.upm.miw.apaw_practice.domain.models.tennis_courts;

import java.math.BigDecimal;

public class Equipment {

    private String type;
    private Integer number;
    private BigDecimal price;
    private final BigDecimal PRICE_PER_UNIT = new BigDecimal(5.0);

    public Equipment(String type, Integer number){
        this.type = type;
        this.number = number;
        this.price = PRICE_PER_UNIT.multiply(new BigDecimal(number));
    }
}
