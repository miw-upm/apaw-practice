package es.upm.miw.apaw_practice.domain.models.vet_clinic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VetComposite implements VetComponent {

    private final String name;
    private final List<VetComponent> vetComponents;

    public VetComposite(String name) {
        this.name = name;
        this.vetComponents = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<VetComponent> getVetComponents() {
        return this.vetComponents;
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(VetComponent vetTree) {
        this.vetComponents.add(vetTree);
    }

    @Override
    public void remove(VetComponent vetTree) {
        this.vetComponents.remove(vetTree);
    }

    @Override
    public int numberOfNodes() {
        return this.vetComponents.size();
    }

}
