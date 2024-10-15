package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Booking;
import es.upm.miw.apaw_practice.domain.models.martial_art.Instructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class InstructorEntity {
    @Indexed(unique = true)
    private String dni;
    private String fullName;
    private Integer phoneNumber;
    private LocalDateTime birthDate;


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public InstructorEntity(String dni, String fullName, Integer phoneNumber, LocalDateTime birthDate) {
        this.dni = dni; ;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public Instructor toInstructor() {
        return new Instructor(dni, fullName, phoneNumber, LocalDateTime.from(birthDate));
    }
    public void fromInstructor(Instructor instructor) {
        BeanUtils.copyProperties(instructor, this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstructorEntity that = (InstructorEntity) o;
        return Objects.equals(dni, that.dni) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(birthDate, that.birthDate) && Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, phoneNumber, birthDate, fullName);
    }

    @Override
    public String toString() {
        return "InstructorEntity{" +
                "dni='" + dni + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", birthDate=" + birthDate +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
