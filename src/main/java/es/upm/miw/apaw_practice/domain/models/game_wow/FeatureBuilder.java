package es.upm.miw.apaw_practice.domain.models.game_wow;

public interface FeatureBuilder {

    interface  Part {
        Optionals part(String part);
    }

    interface Optionals {
        Optionals  spellPower(Integer spellPower);
        Optionals  meleeAtack(Integer meleeatack);
        Optionals  temple(Integer temple);
        Optionals  extraSpell(String extraSpell);
        Feature build();
    }
}
