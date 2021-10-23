package es.upm.miw.apaw_practice.domain.models.car_workshop;

public class CarLeaf implements CarComponent {

    private final Car car;

    public CarLeaf(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return this.car;
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(CarComponent treeCars) {
        // Do nothing because it is a leaf
    }

    @Override
    public void remove(CarComponent treeCars) {
        // Do nothing because it is a leaf
    }

    @Override
    public int numberOfNodes() {
        return 1;
    }
}
