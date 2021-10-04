package es.upm.miw.apaw_practice.domain.models.car_hire;

import java.util.List;

public class Model {

    private String type;
    private String description;
    private Integer enginePower;

    private List<Vehicle> vehicles;

    public Model() {
        //empty for framework
    }

    public Model(String type, String description, Integer enginePower, List<Vehicle> vehicles) {
        this.type = type;
        this.description = description;
        this.enginePower = enginePower;
        this.setVehicles(vehicles);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Model{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", enginePower=" + enginePower +
                ", vehicles=" + vehicles +
                '}';
    }
}
