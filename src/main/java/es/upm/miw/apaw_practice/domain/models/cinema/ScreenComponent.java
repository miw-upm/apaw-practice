package es.upm.miw.apaw_practice.domain.models.cinema;


public interface ScreenComponent {

    boolean isComposite();

    void add(ScreenComponent treeScreen);

    void remove(ScreenComponent treeScreen);

    int numberOfNodes();

}
