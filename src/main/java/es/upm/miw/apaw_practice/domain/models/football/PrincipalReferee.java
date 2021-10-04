package es.upm.miw.apaw_practice.domain.models.football;

public class PrincipalReferee {
    private String name;
    private String cityBorn;
    private Integer age;

    public PrincipalReferee() {
        //empty for framework
    }

    public PrincipalReferee(String name, String cityBorn, Integer age) {
        this.name = name;
        this.cityBorn = cityBorn;
        this.age = age;
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
}
