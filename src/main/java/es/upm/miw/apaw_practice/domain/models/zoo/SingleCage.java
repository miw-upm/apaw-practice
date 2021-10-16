package es.upm.miw.apaw_practice.domain.models.zoo;

public class SingleCage implements CageGroup {

    private Cage cage;

    public SingleCage(Cage cage) {
        this.cage = cage;
    }

    @Override
    public String getLocationCode() {
        return cage.getLocationCode();
    }
}
