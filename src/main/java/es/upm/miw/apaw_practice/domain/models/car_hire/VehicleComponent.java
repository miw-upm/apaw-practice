package es.upm.miw.apaw_practice.domain.models.car_hire;

public interface VehicleComponent {

    void add(VehicleComponent vehicleComponent);

    void remove(VehicleComponent vehicleComponent);

    Boolean isComposite();

    int numberOfNodes();
}
