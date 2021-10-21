package es.upm.miw.apaw_practice.domain.models.department;

public class DepartmentEmployeeIsActiveUpdating {

    private String dni;
    private Boolean isActive;

    public DepartmentEmployeeIsActiveUpdating() {
        //empty for framework
    }

    public DepartmentEmployeeIsActiveUpdating(String dni, Boolean isActive) {
        this.dni = dni;
        this.isActive = isActive;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "DepartmentEmployeeIsActiveUpdating{" +
                "dni='" + dni + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
