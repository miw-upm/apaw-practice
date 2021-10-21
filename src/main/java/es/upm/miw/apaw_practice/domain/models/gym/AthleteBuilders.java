package es.upm.miw.apaw_practice.domain.models.gym;


public interface AthleteBuilders {

    interface Nie {
        Name nie(String nie);
    }

    interface Name {
        FamilyName name(String name);
    }

    interface FamilyName {
        Optionals familyName(String familyName);
    }

    interface Optionals {
        Athlete build();
    }
}
