package es.upm.miw.apaw_practice.domain.models.zoo;

import java.util.Objects;

public class Caretaker {

    private String dni;
    private String name;
    private String surname;

    public Caretaker() {
        //empty from framework
    }
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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
        return "Caretaker{" +
                "DNI='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || obj != null
                && getClass() == obj.getClass()
                && dni.equals(((Caretaker) obj).dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, name, surname);
    }

    public static CaretakerBuilders.Dni builder() {
        return new CaretakerBuilder();
    }

    public static class CaretakerBuilder implements CaretakerBuilders.Dni, CaretakerBuilders.Name,
            CaretakerBuilders.Surname, CaretakerBuilders.Optionals {

        private final Caretaker caretaker;

        public CaretakerBuilder() {
            this.caretaker = new Caretaker();
        }

        @Override
        public CaretakerBuilders.Name dni(String dni) {
            this.caretaker.dni = dni;
            return this;
        }

        @Override
        public CaretakerBuilders.Surname name(String name) {
            this.caretaker.name = name;
            return this;
        }

        @Override
        public CaretakerBuilders.Optionals surname(String surname) {
            this.caretaker.surname = surname;
            return this;
        }

        @Override
        public Caretaker build() {
            return this.caretaker;
        }
    }
}
