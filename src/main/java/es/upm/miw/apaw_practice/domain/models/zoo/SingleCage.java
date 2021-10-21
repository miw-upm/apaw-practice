package es.upm.miw.apaw_practice.domain.models.zoo;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.CageEntity;

public class SingleCage implements CageGroup {

    private Cage cage;

    public SingleCage(Cage cage) {
        this.cage = cage;
    }

    @Override
    public String getLocationCode() {
        return cage.getLocationCode();
    }

    @Override
    public void add(CageGroup cageGroup) {
        //do nothing because it is a leaf
    }

    @Override
    public void remove(CageGroup cageGroup) {
        //do nothing because it is a leaf
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || obj != null
                && getClass() == obj.getClass()
                && ((getLocationCode().equals(((CageGroup) obj).getLocationCode())));
    }
}
