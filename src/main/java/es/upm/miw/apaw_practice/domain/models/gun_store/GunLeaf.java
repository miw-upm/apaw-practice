package es.upm.miw.apaw_practice.domain.models.gun_store;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class GunLeaf extends GunComponent {

    private final Gun gun;

    public GunLeaf(Gun gun) {
        this.gun = gun;
    }

    @Override
    public void add(GunComponent gunComponent) {
        throw new UnsupportedOperationException("This component is not composite");
    }

    @Override
    public void remove(GunComponent gunComponent) {
        throw new UnsupportedOperationException("This component is not composite");
    }

    @Override
    public Boolean isComposite() {
        return false;
    }

    @Override
    public Integer getNumberOfGuns() {
        return 1;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return gun.getPrice();
    }

    @Override
    public Stream<Accesory> getAccesories() {
        return this.gun.getAccesories().stream();
    }

    @Override
    public Stream<CompatibleAmmo> getCompatibleAmmos() {
        return Stream.of(gun.getAmmo());
    }
}
