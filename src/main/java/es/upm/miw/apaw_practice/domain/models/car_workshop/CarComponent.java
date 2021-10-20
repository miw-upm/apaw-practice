package es.upm.miw.apaw_practice.domain.models.car_workshop;

public interface CarComponent {

    boolean isComposite();

    void add(CarComponent treeCars);

    void remove(CarComponent treeCars);

    int numberOfNodes();

}
