package es.upm.miw.apaw_practice.domain.models.tennis_courts.compositePlayer;

public interface TreePlayer {

    String dni();

    String reference();

    boolean isComposite();

    void add(TreePlayer treePlayer);

    void remove(TreePlayer treePlayer);
}
