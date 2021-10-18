package es.upm.miw.apaw_practice.domain.models.car_hire;

public class VehicleLeaf implements VehicleComponent{

    private final Vehicle vehicle;

    public VehicleLeaf(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void add(VehicleComponent vehicleComponent) {
        throw new UnsupportedOperationException("Unsupported operation in Leaf, can not 'add' anything to a 'Leaf'.");
    }

    @Override
    public void remove(VehicleComponent vehicleComponent) {
        //Do nothing because is a Leaf
    }

    @Override
    public Boolean isComposite() {
        return false;
    }

    @Override
    public int numberOfNodes() {
        return 1;
    }
}
