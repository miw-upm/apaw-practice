package es.upm.miw.apaw_practice.domain.models.football;

public interface PrincipalRefereeBuilders {

    interface Name {
        CityBorn name(String name);
    }

    interface CityBorn {
        Age cityBorn(String cityBorn);
    }

    interface Age {
        PrincipalRefereeBuild age(Integer age);
    }

    interface PrincipalRefereeBuild {
        PrincipalReferee build();
    }

}
