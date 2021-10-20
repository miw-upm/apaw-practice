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
       // Nothing because it's a leaf

    }

    @Override
    public void remove(TreeDirectors treeDirector) {
        // Nothing because it's a leaf

    }

    @Override
    public int numberOfNodes() {
        return 1;
    }

}
