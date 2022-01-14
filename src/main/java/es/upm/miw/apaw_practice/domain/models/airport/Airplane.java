package es.upm.miw.apaw_practice.domain.models.airport;

import java.time.LocalDate;

public class Airplane {
    private String serialNumber;
    private String manufacturer;
    private String model;
    private LocalDate buildDate;
    private Integer capacity;

    public Airplane() {
        // empty for framework
    }

    public Airplane(String serialNumber, String manufacturer, String model, LocalDate buildDate, Integer capacity) {
        this.serialNumber = serialNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.buildDate = buildDate;
        this.capacity = capacity;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(LocalDate buildDate) {
        this.buildDate = buildDate;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "serialNumber='" + this.serialNumber + '\'' +
                ", manufacturer='" + this.manufacturer + '\'' +
                ", model='" + this.model + '\'' +
                ", buildDate='" + this.buildDate + '\'' +
                ", capacity=" + this.capacity +
                '}';
    }
}
