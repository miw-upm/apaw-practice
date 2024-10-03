package es.upm.miw.apaw_practice.domain.models.car;

public class Manufacturer {
    private String name;
    private String country;
    private Integer numberOfEmployees;


    public Manufacturer() {
        //empty for framework
    }
    public Manufacturer(String name, String country, Integer numberOfEmployees) {
        this.name = name;
        this.country = country;
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }
    @Override
    public String toString() {
        return "Manufacturer{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", numberOfEmployees=" + numberOfEmployees +
                '}';
    }
}
