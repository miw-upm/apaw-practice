package es.upm.miw.apaw_practice.domain.models.vet_clinic;

public class Vet {
    private Integer number;
    private String name;
    private String surname;

    public Vet(){
        //empty for framework
    }

    public Vet(Integer number, String name, String surname){
        this.number = number;
        this.name = name;
        this.surname = surname;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Vet{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
