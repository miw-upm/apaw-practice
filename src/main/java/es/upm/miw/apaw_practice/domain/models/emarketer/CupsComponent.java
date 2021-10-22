package es.upm.miw.apaw_practice.domain.models.emarketer;

import es.upm.miw.apaw_practice.domain.models.car_hire.VehicleComponent;

public interface CupsComponent {

    void add(CupsComponent cupsComponent);

    void remove(CupsComponent cupsComponent);

    Boolean isComposite();

    int numberOfNodes();

    int numberOfLeafNodes();

    int numberOfCompositeNodes();

}
