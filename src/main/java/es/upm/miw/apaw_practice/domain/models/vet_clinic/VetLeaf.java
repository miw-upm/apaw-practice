package es.upm.miw.apaw_practice.domain.models.vet_clinic;

public class VetLeaf implements VetComponent{

    private final Vet vet;

    public VetLeaf(Vet vet) {
        this.vet = vet;
    }

    public Vet getVet() {
        return this.vet;
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(VetComponent vetTree) {
        throw new UnsupportedOperationException("Unsupported operation: can not 'add' anything to a 'Leaf'.");
    }

    @Override
    public void remove(VetComponent vetTree) {
        //do nothing because it is a leaf
    }

    @Override
    public int numberOfNodes() {
        return 1;
    }
}
