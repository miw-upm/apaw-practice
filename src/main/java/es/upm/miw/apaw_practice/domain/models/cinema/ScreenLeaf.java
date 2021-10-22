package es.upm.miw.apaw_practice.domain.models.cinema;

import es.upm.miw.apaw_practice.domain.models.car_workshop.CarComponent;

public class ScreenLeaf implements CarComponent {

    private final Screen screen;

    public ScreenLeaf(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreen() {
        return this.screen;
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
