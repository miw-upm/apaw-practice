package es.upm.miw.apaw_practice.domain.models.pharmacy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreePharmaciesComposite implements TreePharmacies {

    private final String address;

    private final List<TreePharmacies> treePharmaciesList;

    public TreePharmaciesComposite(String address) {
        this.address = address;
        this.treePharmaciesList = new ArrayList<>();
    }

    @Override
    public String address() {
        return this.address;
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(TreePharmacies treePharmacies) {
        this.treePharmaciesList.add(treePharmacies);
    }

    @Override
    public void remove(TreePharmacies treePharmacies) {
        this.treePharmaciesList.remove(treePharmacies);
    }

    @Override
    public List<Pharmacy> getPharmacies() {
        return this.treePharmaciesList.stream()
                .flatMap(treePharmacies -> treePharmacies.getPharmacies().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
