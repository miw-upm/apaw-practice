package es.upm.miw.apaw_practice.domain.models.zoo;

public class Animal {

    private String family;
    private String diet;
    private Integer age;

    public Animal(String family, String diet, Integer age) {
        this.family = family;
        this.diet = diet;
        this.age = age;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "family='" + family + '\'' +
                ", diet='" + diet + '\'' +
                ", age=" + age +
                '}';
    }
}
