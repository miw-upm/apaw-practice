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
        //Do nothing because it is a leaf
    }

    @Override
    public void remove(TreeDirectors treeDirector) {
        //Do nothing because it is a leaf
    }
}
