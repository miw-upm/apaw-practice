package es.upm.miw.apaw_practice.domain.models.gun_store;

import java.math.BigDecimal;

public interface AccesoryBuilders {
    interface AccesoryId {
        Category accesoryId(Integer accesoryId);
    }

    interface Category {
        Price category(String category);
    }

    interface Price {
        Builder price(BigDecimal price);
    }

    interface Builder {
        Accesory build();
    }

}
