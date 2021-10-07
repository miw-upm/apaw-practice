package es.upm.miw.apaw_practice.domain.models.zoo;

public class Animal {

    private String name;
    private String family;
    private String diet;

    public Animal() {
        //empty from framework
    }

    public Animal(String name, String family, String diet) {
        this.name = name;
        this.family = family;
        this.diet = diet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", diet='" + diet + '\'' +
                '}';
    }
}
