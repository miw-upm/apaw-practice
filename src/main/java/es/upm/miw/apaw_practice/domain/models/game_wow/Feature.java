package es.upm.miw.apaw_practice.domain.models.game_wow;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.FeatureEntity;

public class Feature {

    private String part;
    private Integer spellPower;
    private Integer meleeAtack;
    private Integer temple;
    private String extraSpell;

    public Feature() {
        //empty for framework
    }

    public static FeatureBuilder.Part builder() {return new Builder();}

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public Integer getSpellPower() {
        return spellPower;
    }

    public void setSpellPower(Integer spellPower) {
        this.spellPower = spellPower;
    }

    public Integer getMeleeAtack() {
        return meleeAtack;
    }

    public void setMeleeAtack(Integer meleeAtack) {
        this.meleeAtack = meleeAtack;
    }

    public Integer getTemple() {
        return temple;
    }

    public void setTemple(Integer temple) {
        this.temple = temple;
    }

    public String getExtraSpell() {
        return extraSpell;
    }

    public void setExtraSpell(String extraSpell) {
        this.extraSpell = extraSpell;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "part='" + part + '\'' +
                ", spellPower=" + spellPower +
                ", meleeAtack=" + meleeAtack +
                ", temple=" + temple +
                ", extraEpell='" + extraSpell + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        if(this == obj)
            return true;

        Feature objFeature = (Feature) obj;

        if(this.part.equals(objFeature.part) &&
           this.spellPower == objFeature.spellPower &&
           this.meleeAtack == objFeature.meleeAtack &&
           this.temple == objFeature.temple &&
           ((this.extraSpell == null && objFeature.extraSpell == null) || (this.extraSpell != null && this.extraSpell.equals(objFeature.extraSpell))))
            return true;

        return false;
    }

    public FeatureEntity toFeatureEntity(Feature feature) {
        return new FeatureEntity(feature);
    }

    public static class Builder implements FeatureBuilder.Part, FeatureBuilder.Optionals {

        private final Feature feature;

        public Builder() {
            this.feature = new Feature();
        }

        @Override
        public FeatureBuilder.Optionals part(String part) {
            this.feature.part = part;
            return this;
        }

        @Override
        public FeatureBuilder.Optionals spellPower(Integer spellPower) {
            this.feature.spellPower = spellPower;
            return this;
        }

        @Override
        public FeatureBuilder.Optionals meleeAtack(Integer meleeatack) {
            this.feature.meleeAtack = meleeatack;
            return this;
        }

        @Override
        public FeatureBuilder.Optionals temple(Integer temple) {
            this.feature.temple = temple;
            return this;
        }

        @Override
        public FeatureBuilder.Optionals extraSpell(String extraSpell) {
            this.feature.extraSpell = extraSpell;
            return this;
        }

        @Override
        public Feature build() {
            return this.feature;
        }
    }
}
