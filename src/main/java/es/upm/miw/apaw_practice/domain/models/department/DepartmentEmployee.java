package es.upm.miw.apaw_practice.domain.models.department;

import java.time.LocalDate;

public class DepartmentEmployee {
    private String dni;
    private LocalDate birthday;
    private Boolean isActive;
    private Manager manager;

    public DepartmentEmployee() {
        //empty for framework
    }

    public DepartmentEmployee(String dni, LocalDate birthday, Boolean isActive, Manager manager) {
        this.dni = dni;
        this.birthday = birthday;
        this.isActive = isActive;
        this.manager = manager;
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "DepartmentEmployee{" +
                "dni='" + dni + '\'' +
                ", birthday=" + birthday +
                ", isActive=" + isActive +
                ", manager=" + manager +
                '}';
    }
}
