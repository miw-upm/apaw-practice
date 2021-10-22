package es.upm.miw.apaw_practice.domain.models.vet_clinic;

public interface VetComponent {
    boolean isComposite();
    void add(VetComponent vetTree);
    void remove(VetComponent vetTree);
    int numberOfNodes();
}
