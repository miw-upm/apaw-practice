package es.upm.miw.apaw_practice.domain.models.department;

import java.time.LocalDate;

public class DepartmentEmployee {
    private String dni;
    private LocalDate birthday;
    private Boolean isActive;

    public DepartmentEmployee() {
        //empty for framework
    }

    public DepartmentEmployee(String dni, LocalDate birthday, Boolean isActive) {
        this.dni = dni;
        this.birthday = birthday;
        this.isActive = isActive;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "DepartmentEmployee{" +
                "dni='" + dni + '\'' +
                ", birthday=" + birthday +
                ", isActive=" + isActive +
                '}';
    }
}
