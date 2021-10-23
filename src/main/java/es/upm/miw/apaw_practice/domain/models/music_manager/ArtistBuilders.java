package es.upm.miw.apaw_practice.domain.models.music_manager;

public interface ArtistBuilders {

    interface FirstName {
        FamilyName firstName(String firstName);
    }

    interface FamilyName {
        Age familyName(String familyName);
    }

    interface Age {
        Optionals age(Integer age);
    }

    interface Optionals {
        Artist build();
    }
}
