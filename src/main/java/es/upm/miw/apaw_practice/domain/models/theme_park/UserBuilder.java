package es.upm.miw.apaw_practice.domain.models.theme_park;

import java.time.LocalDateTime;

 interface UserBuilder {
     interface IdMembership {
        Address idMembership(String idMembership);
    }

     interface Address {
        EntranceDate address(String address);
    }

     interface EntranceDate {
        OneYearMembership entranceDate(LocalDateTime entranceDate);
    }

     interface OneYearMembership {
        Builder oneYearMembership(Boolean oneYearMembership);
    }

     interface Builder {
        User build();
    }
}
