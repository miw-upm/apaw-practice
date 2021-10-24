package es.upm.miw.apaw_practice.domain.models.pharmacy;

import java.util.List;

public interface TreePharmacies {
    String address();

    boolean isComposite();

    void add(TreePharmacies treePharmacies);

    void remove(TreePharmacies treePharmacies);

    List<Pharmacy> getPharmacies();
}
