package es.upm.miw.apaw_practice.domain.models.art_museum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ExhibitionBuilders {
    interface Name {
        DateOfExhibition name(String name);
    }

    interface DateOfExhibition {
        Optionals dateOfExhibition(LocalDateTime dateOfExhibition);
    }

    interface Optionals {
        Optionals price(BigDecimal price);
        Exhibition build();
    }
}
