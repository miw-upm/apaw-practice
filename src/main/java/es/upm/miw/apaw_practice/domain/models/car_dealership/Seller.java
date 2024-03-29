package es.upm.miw.apaw_practice.domain.models.car_dealership;

import java.util.Objects;

public class Seller {
    private String id;
    private String name;
    private String surname;
    private Integer salary;

    public Seller() {
        //empty for framework
    }
    public Seller(String name, String surname, Integer salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(id, seller.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                '}';
    }

    public static class SellerBuilder {
        private final Seller seller;
        private final Car.CarBuilder carBuilder;

        public SellerBuilder(Car.CarBuilder carBuilder) {
            this.seller = new Seller();
            this.carBuilder = carBuilder;
        }

        public SellerBuilder name(String name) {
            this.seller.setName(name);
            return this;
        }

        public SellerBuilder surname(String surname) {
            this.seller.setSurname(surname);
            return this;
        }

        public SellerBuilder salary(Integer salary) {
            this.seller.setSalary(salary);
            return this;
        }

        public Car.CarBuilder build() {
            carBuilder.addSeller(this.seller);
            return this.carBuilder;
        }
    }
}
