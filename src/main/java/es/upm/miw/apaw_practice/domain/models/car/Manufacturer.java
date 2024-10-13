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

    public static ManufacturerBuilders.Name builder() {
        return new Builder();
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

    public static class Builder implements ManufacturerBuilders.Name, ManufacturerBuilders.Country, ManufacturerBuilders.NumberOfEmployees, ManufacturerBuilders.Builder{

        private final Manufacturer instance;

        public Builder() {
            this.instance = new Manufacturer();
        }

        @Override
        public ManufacturerBuilders.Country name(String name) {
            this.instance.name = name;
            return this;
        }

        @Override
        public ManufacturerBuilders.NumberOfEmployees country(String country) {
            this.instance.country = country;
            return this;
        }

        @Override
        public ManufacturerBuilders.Builder numberOfEmployees(Integer numberOfEmployees) {
            this.instance.numberOfEmployees = numberOfEmployees;
            return this;
        }

        @Override
        public Manufacturer build() {
            return this.instance;
        }
    }
}
