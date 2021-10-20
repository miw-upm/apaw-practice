package es.upm.miw.apaw_practice.domain.models.hotel;

public class TreeDirectorsLeaf implements TreeDirectors {

    private final Director director;

    public TreeDirectorsLeaf(Director director) {
        this.director = director;
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(TreeDirectors treeDirector) {
        throw new UnsupportedOperationException("Unsupported operation in Leaf, can not 'add' anything to a 'Leaf'.");

    }

    @Override
    public void remove(TreeDirectors treeDirector) {
        throw new UnsupportedOperationException("Unsupported operation in Leaf, can not 'remove' anything to a 'Leaf'.");

    }

    @Override
    public int numberOfNodes() {
        return 1;
    }
}
