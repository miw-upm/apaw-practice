package es.upm.miw.apaw_practice.domain.models.night_life;

public interface TreeOwners {
    String getName();
    String getPhone();
    String getEmail();
    boolean isComposite();
    void add (TreeOwners treeOwners);
    void remove (TreeOwners treeOwners);

}
