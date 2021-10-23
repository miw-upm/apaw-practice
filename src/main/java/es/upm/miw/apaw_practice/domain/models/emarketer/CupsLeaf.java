package es.upm.miw.apaw_practice.domain.models.emarketer;

public class CupsLeaf implements CupsComponent{

    private final Cups cups;

    public CupsLeaf(Cups cups) {
        this.cups = cups;
    }

    public Cups getCups() {
        return this.cups;
    }

    @Override
    public void add(CupsComponent CupsComponent) {
        throw new UnsupportedOperationException("Unsupported operation in Leaf, can not 'add' anything to a 'Leaf'.");
    }

    @Override
    public void remove(CupsComponent CupsComponent) {
        //Do nothing because is a Leaf
    }

    @Override
    public Boolean isComposite() {
        return false;
    }

    @Override
    public int numberOfNodes() {
        return 1;
    }

    @Override
    public int numberOfLeafNodes() {
        return 1;
    }

    @Override
    public int numberOfCompositeNodes() {
        return 0;
    }

}
