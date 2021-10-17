package es.upm.miw.apaw_practice.domain.models.vet_clinic;

import java.util.List;

public class Vet {
    private Integer vetNumber;
    private String name;
    private String surname;

    public Vet(){
        //empty for framework
    }

    public Vet(Integer vetNumber, String name, String surname){
        this.vetNumber = vetNumber;
        this.name = name;
        this.surname = surname;
    }

    public Integer getVetNumber() {
        return vetNumber;
    }

    public void setVetNumber(Integer vetNumber) {
        this.vetNumber = vetNumber;
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
                "vet number=" + vetNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
