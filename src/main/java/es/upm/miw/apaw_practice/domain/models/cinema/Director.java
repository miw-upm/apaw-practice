package es.upm.miw.apaw_practice.domain.models.cinema;

import java.time.LocalDate;
import java.util.Objects;

public class Director {
    private final String dni;
    private final LocalDate birthdate;
    private final String style;

    private Director(Builder builder) {
        this.dni = builder.dni;
        this.birthdate = builder.birthdate;
        this.style = builder.style;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getStyle() {
        return style;
    }

    // Builder interno, atributos en orden y obligatorios
    public static class Builder implements Build<Director> {
        private String dni;
        private LocalDate birthdate;
        private String style;

        public Builder dni(String dni) {
            this.dni = dni;
            return this;
        }
        public Builder birthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
            return this;
        }
        public Builder style(String style) {
            this.style = style;
            return this;
        }
        @Override
        public Director build() {
            Objects.requireNonNull(dni, "dni required");
            Objects.requireNonNull(birthdate, "birthdate required");
            Objects.requireNonNull(style, "style required");
            return new Director(this);
        }
    }
}