package es.upm.miw.apaw_practice.domain.models.gym;

public class Athlete {
    private String nie;
    private String name;
    private String familyName;

    public Athlete() {
        //empty for framework

    }

    //TO delete

    public String getNie() {
        return nie;
    }

    public void setNie(String nie) {
        this.nie = nie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyname(String familyname) {
        this.familyName = familyname;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "nie='" + nie + '\'' +
                ", name='" + name + '\'' +
                ", familyname='" + familyName + '\'' +
                '}';
    }

    public static AthleteBuilders.Nie builder() {
        return new Builder();
    }

    public static Athlete nameof(Athlete athlete) {
        Athlete athlete1 = new Athlete();
        athlete1.setName(athlete.getName());
        return athlete1;
    }

    public static class Builder implements AthleteBuilders.Nie, AthleteBuilders.Name,
            AthleteBuilders.FamilyName, AthleteBuilders.Optionals {

        private final Athlete athlete;

        public Builder() {
            this.athlete = new Athlete();
        }

        @Override
        public AthleteBuilders.Name nie(String nie) {
            this.athlete.nie = nie;
            return this;
        }

        @Override
        public AthleteBuilders.FamilyName name(String name) {
            this.athlete.name = name;
            return this;
        }

        @Override
        public AthleteBuilders.Optionals familyName(String familyName) {
            this.athlete.familyName = familyName;
            return this;
        }

        @Override
        public Athlete build() {
            return this.athlete;
        }
    }
}
