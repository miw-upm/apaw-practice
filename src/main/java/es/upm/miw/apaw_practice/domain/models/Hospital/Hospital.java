package es.upm.miw.apaw_practice.domain.models.shop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Hospital{
    private Long id;
    private  String name;
    private Integer Capacity;
    private String location;

    public Hospital() {
    }

    public Hospital(Long id, String name, Integer capacity, String location) {
        this.id = id;
        this.name = name;
        Capacity = capacity;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return Capacity;
    }

    public void setCapacity(Integer capacity) {
        Capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Hospital{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Capacity=" + Capacity +
                ", location='" + location + '\'' +
                '}';
    }
}