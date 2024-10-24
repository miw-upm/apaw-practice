package es.upm.miw.apaw_practice.domain.models.car;

import java.time.LocalDate;

public class OwnerCar {
    private String name;
    private String driverLicense;
    private LocalDate birthDate;

    public OwnerCar() {
        //empty for framework
    }
    public OwnerCar(String name, String driverLicense, LocalDate birthDate) {
        this.name = name;
        this.driverLicense = driverLicense;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "OwnerClinic{" +
                "name='" + name + '\'' +
                ", driverLicense='" + driverLicense + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}