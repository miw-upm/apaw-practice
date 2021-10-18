package es.upm.miw.apaw_practice.domain.models.car_hire;

import java.util.ArrayList;
import java.util.List;

public class VehicleComposite implements VehicleComponent{

    private final String name;
    private final List<VehicleComponent> vehicleComponents;

    public VehicleComposite(String name) {
        this.name = name;
        this.vehicleComponents = new ArrayList<>();
    }

    @Override
    public void add(VehicleComponent vehicleComponent) {
        this.vehicleComponents.add(vehicleComponent);
    }

    @Override
    public void remove(VehicleComponent vehicleComponent) {
        this.vehicleComponents.remove(vehicleComponent);
    }

    @Override
    public Boolean isComposite() {
        return true;
    }

    @Override
    public int numberOfNodes() {
        int sum = 1;
        for(VehicleComponent vehicle : vehicleComponents) {
            sum += vehicle.numberOfNodes();
        }
        return sum;
    }

    public List<VehicleComponent> getVehicleComponents() {
        return this.vehicleComponents;
    }

    public String getName() {
        return this.name;
    }
}
