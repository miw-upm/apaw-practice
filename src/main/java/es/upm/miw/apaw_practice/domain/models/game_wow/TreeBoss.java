package es.upm.miw.apaw_practice.domain.models.game_wow;

public interface TreeBoss {

    String description();
    String effort();
    boolean isComposite();
    void add(TreeBoss treeBoss);
    void remove (TreeBoss treeBoss);
}
