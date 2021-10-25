package es.upm.miw.apaw_practice.domain.models.football;

public class PrincipalReferee {
    private String name;
    private String cityBorn;
    private Integer age;

    public PrincipalReferee() {
        //empty for framework
    }

    public static PrincipalRefereeBuilders.Name builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityBorn() {
        return cityBorn;
    }

    public void setCityBorn(String cityBorn) {
        this.cityBorn = cityBorn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PrincipalReferee{" +
                "name=" + name +
                ", cityBorn=" + cityBorn +
                ", age=" + age +
                '}';
    }

    public static class Builder implements PrincipalRefereeBuilders.Name, PrincipalRefereeBuilders.CityBorn, PrincipalRefereeBuilders.Age, PrincipalRefereeBuilders.PrincipalRefereeBuild {

        private final PrincipalReferee principalReferee;

        public Builder() {
            this.principalReferee = new PrincipalReferee();
        }

        @Override
        public PrincipalRefereeBuilders.CityBorn name(String name) {
            this.principalReferee.name = name;
            return this;
        }

        @Override
        public PrincipalRefereeBuilders.Age cityBorn(String cityBorn) {
            this.principalReferee.cityBorn = cityBorn;
            return this;
        }

        @Override
        public PrincipalRefereeBuilders.PrincipalRefereeBuild age(Integer age) {
            this.principalReferee.age = age;
            return this;
        }

        @Override
        public PrincipalReferee build() {
            return this.principalReferee;
        }
    }
}
