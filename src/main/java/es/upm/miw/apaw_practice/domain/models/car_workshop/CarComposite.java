package es.upm.miw.apaw_practice.domain.models.car_workshop;

import java.util.ArrayList;
import java.util.List;

public class CarComposite implements CarComponent {

    private final String name;
    private final List<CarComponent> carComponents;

    public CarComposite(String name) {
        this.name = name;
        this.carComponents = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(CarComponent treeCar) {
        this.carComponents.add(treeCar);
    }

    @Override
    public void remove(CarComponent treeCar) {
        this.carComponents.remove(treeCar);
    }

    @Override
    public int numberOfNodes() {
        return this.carComponents.size();
    }
}
