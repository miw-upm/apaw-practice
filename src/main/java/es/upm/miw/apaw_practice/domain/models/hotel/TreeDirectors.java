package es.upm.miw.apaw_practice.domain.models.hotel;

public interface TreeDirectors {

    boolean isComposite();

    void add(TreeDirectors treeDirector);

    void remove(TreeDirectors treeDirector);

    int numberOfNodes();
}
