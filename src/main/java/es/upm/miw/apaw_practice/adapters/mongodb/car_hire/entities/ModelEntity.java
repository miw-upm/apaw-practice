package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities;

import es.upm.miw.apaw_practice.domain.models.car_hire.Model;
import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class ModelEntity {

    @Id
    private String id;
    private String type;
    private String description;
    private Integer enginePower;
    @DBRef
    private List<VehicleEntity> vehicleEntities;

    public ModelEntity() {
        //empty for framework
    }

    public ModelEntity(Model model) {
        BeanUtils.copyProperties(model, this, "vehicleList");
        this.vehicleEntities = new ArrayList<>();
        this.setVehicleEntities(model.getVehicleList().stream()
                .map(this::vehicleToEntity)
                .collect(Collectors.toList())
        );
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<VehicleEntity> getVehicleEntities() {
        return vehicleEntities;
    }

    public void setVehicleEntities(List<VehicleEntity> vehicleEntities) {
        this.vehicleEntities = vehicleEntities;
    }

    public VehicleEntity vehicleToEntity(Vehicle vehicle) {
        VehicleEntity vehicleEntity = new VehicleEntity();
        BeanUtils.copyProperties(vehicle, vehicleEntity);
        return vehicleEntity;
    }

    public Model toModel() {
        Model model = new Model();
        BeanUtils.copyProperties(this, model, "vehicleEntities");
        this.getVehicleEntities().forEach(vehicleEntity -> model.addVehicleToList(vehicleEntity.toVehicle()));
        return model;
    }

    @Override
    public String toString() {
        return "ModelEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", enginePower=" + enginePower +
                ", vehicleEntities=" + vehicleEntities +
                '}';
    }
}
