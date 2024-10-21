package es.upm.miw.apaw_practice.domain.models.martial_art;

public interface StyleBuilders {

    interface Name {
        Description name(String name);
    }

    interface Description {
        Popularity description(String description);
    }

    interface Popularity {
        OriginCountry popularity(Integer popularity);
    }

    interface OriginCountry {
        Optionals originCountry(String originCountry);
    }

    interface Optionals {
        Style build();
    }
}
