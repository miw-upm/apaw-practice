package es.upm.miw.apaw_practice.domain.models.pharmacy;


import java.math.BigDecimal;

public interface DrugBuilders {

    interface Barcode {
        Name barcode(String dni);
    }

    interface Name {
        Commercialized name(String name);
    }

    interface Commercialized {
        Price commercialized(Boolean commercialized);
    }

    interface Price {
        Optionals price(BigDecimal price);
    }

    interface Optionals {
        Drug build();
    }


}
