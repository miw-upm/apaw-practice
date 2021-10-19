package es.upm.miw.apaw_practice.domain.models.hotel;

public interface TreeDirectors {

    String dni();

    String email();

    Integer telephone();

    boolean isComposite();

    void add(TreeDirectors treeDirector);

    void remove(TreeDirectors treeDirector);

    int numberOfNodes();
}
