package es.upm.miw.apaw_practice.domain.models.martial_art;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Instructor {
    private String dni;
    private String fullName;
    private Integer phoneNumber;
    private LocalDateTime birthDate;

    public Instructor() {
        // empty for framework
    }

    public Instructor(String dni, String fullName, Integer phoneNumber, LocalDateTime birthDate) {
        this.dni = dni;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }
    // Método estático para iniciar el proceso de construcción
    public static InstructorBuilders.Dni builder() {
        return new Builder();
    }

    // Builder interno con pasos definidos a través de interfaces
    public static class Builder implements InstructorBuilders.Dni, InstructorBuilders.FullName, InstructorBuilders.PhoneNumber, InstructorBuilders.BirthDate, InstructorBuilders.Optionals {

        private final Instructor instructor;

        public Builder() {
            this.instructor = new Instructor();
        }

        @Override
        public InstructorBuilders.FullName dni(String dni) {
            this.instructor.dni = dni;
            return this;
        }

        @Override
        public InstructorBuilders.PhoneNumber fullName(String fullName) {
            this.instructor.fullName = fullName;
            return this;
        }


        @Override
        public InstructorBuilders.BirthDate phoneNumber(Integer phoneNumber) {
            this.instructor.phoneNumber = phoneNumber;
            return this;
        }

        @Override
        public InstructorBuilders.Optionals birthDate(LocalDateTime birthDate) {
            this.instructor.birthDate = birthDate;
            return this;
        }

        @Override
        public Instructor build() {
            return this.instructor;
        }
    }


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "dni='" + dni + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", birthDate=" + birthDate +
                '}';
    }
}
