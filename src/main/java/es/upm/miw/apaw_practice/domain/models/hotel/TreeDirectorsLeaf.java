package es.upm.miw.apaw_practice.domain.models.hotel;

public class TreeDirectorsLeaf implements TreeDirectors {

    private final Director director;

    public TreeDirectorsLeaf(Director director) {
        this.director = director;
    }

    @Override
    public String dni() {
        return this.director.getDni();
    }

    @Override
    public String email() {
        return this.director.getEmail();
    }

    @Override
    public Integer telephone() {
        return this.director.getTelephone();
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
