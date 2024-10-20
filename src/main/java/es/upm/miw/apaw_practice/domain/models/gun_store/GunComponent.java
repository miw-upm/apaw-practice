package es.upm.miw.apaw_practice.domain.models.gun_store;

import java.math.BigDecimal;
import java.util.stream.Stream;

public abstract class GunComponent {
    public abstract void add(GunComponent gunComponent);

    public abstract void remove(GunComponent gunComponent);

    public abstract Integer getNumberOfGuns();

    public abstract BigDecimal getTotalPrice();

    public abstract Boolean isComposite();

    public abstract Stream<Accesory> getAccesories();

    public abstract Stream<CompatibleAmmo> getCompatibleAmmos();
}
