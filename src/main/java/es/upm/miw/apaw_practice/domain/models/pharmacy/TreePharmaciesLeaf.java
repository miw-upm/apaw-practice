package es.upm.miw.apaw_practice.domain.models.pharmacy;

import es.upm.miw.apaw_practice.domain.models.music_manager.Album;

import java.util.List;

public class TreePharmaciesLeaf implements TreePharmacies {
    private final Pharmacy pharmacy;

    public TreePharmaciesLeaf(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    @Override
    public String address() {
        return this.pharmacy.getAddress();
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(TreePharmacies treePharmacies) {
        // It is a leaf
    }

    @Override
    public void remove(TreePharmacies treePharmacies) {
        // It is a leaf
    }

    @Override
    public List<Pharmacy> getPharmacies() {
        return List.of(this.pharmacy);
    }
}
