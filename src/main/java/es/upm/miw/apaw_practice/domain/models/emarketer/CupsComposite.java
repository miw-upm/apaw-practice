package es.upm.miw.apaw_practice.domain.models.emarketer;

import java.util.ArrayList;
import java.util.List;

public class CupsComposite implements CupsComponent {

    private final String name;
    private final List<CupsComponent> cupsComponents;

    public CupsComposite(String name) {
        this.name = name;
        this.cupsComponents = new ArrayList<>();
    }


    public List<CupsComponent> getCupsComponents() {
        return this.cupsComponents;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void add(CupsComponent cupsComponent) {
        this.cupsComponents.add(cupsComponent);
    }

    @Override
    public void remove(CupsComponent cupsComponent) {
        this.cupsComponents.remove(cupsComponent);
    }

    @Override
    public Boolean isComposite() {
        return true;
    }

    @Override
    public int numberOfNodes() {
        int sum = 1;
        for (CupsComponent cups : cupsComponents) {
            sum += cups.numberOfNodes();
        }
        return sum;
    }

    @Override
    public int numberOfLeafNodes() {
        int sum = 0;
        for (CupsComponent cups : cupsComponents) {
            sum += cups.numberOfLeafNodes();
        }
        return sum;
    }

    @Override
    public int numberOfCompositeNodes() {
        int sum = 1;
        for (CupsComponent cups : cupsComponents) {
            sum += cups.numberOfCompositeNodes();
        }
        return sum;
    }

}
