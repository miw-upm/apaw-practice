package es.upm.miw.apaw_practice.domain.models.cinema;

public class ScreenLeaf implements ScreenComponent {

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
    public void add(ScreenComponent treeScreen) {
        // Do nothing because it is a leaf
    }

    @Override
    public void remove(ScreenComponent treeScreen) {
        // Do nothing because it is a leaf
    }

    @Override
    public int numberOfNodes() {
        return 1;
    }
}
