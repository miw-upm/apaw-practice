package es.upm.miw.apaw_practice.domain.models.cinema;

import java.util.ArrayList;
import java.util.List;

public class ScreenComposite implements ScreenComponent{

    private final String name;
    private final List<ScreenComponent> screenComponents;

    public ScreenComposite(String name) {
        this.name = name;
        this.screenComponents = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<ScreenComponent> getScreenComponents() {
        return screenComponents;
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(ScreenComponent treeScreen) {
        this.screenComponents.add(treeScreen);
    }

    @Override
    public void remove(ScreenComponent treeScreen) {
        this.screenComponents.remove(treeScreen);
    }

    @Override
    public int numberOfNodes() {
        return this.screenComponents.size();
    }
}
