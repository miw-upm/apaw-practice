package es.upm.miw.apaw_practice.domain.models.car_hire;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private String type;
    private String description;
    private Integer enginePower;

    private List<Vehicle> vehicleList = new ArrayList<Vehicle>();

    public Model() {
        //empty for framework
    }

    public Model(String type, String description, Integer enginePower, List<Vehicle> vehicleList) {
        this.type = type;
        this.description = description;
        this.enginePower = enginePower;
        this.setVehicleList(vehicleList);
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

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public void addVehicleToList(Vehicle vehicle) {
        this.vehicleList.add(vehicle);
    }

    @Override
    public String toString() {
        return "Model{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", enginePower=" + enginePower +
                ", vehicleList=" + vehicleList +
                '}';
    }
}
