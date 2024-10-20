package es.upm.miw.apaw_practice.domain.models.gun_store;

import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GunComposite extends GunComponent{
    private final List<GunComponent> gunComponents;

    public GunComposite() {
        this(new ArrayList<>());
    }
    public GunComposite(List<GunComponent> gunComponents) {
        this.gunComponents = gunComponents;
    }

    @Override
    public void add(GunComponent gunComponent) {
        this.gunComponents.add(gunComponent);
    }

    @Override
    public void remove(GunComponent gunComponent) {
        this.gunComponents.remove(gunComponent);
    }

    @Override
    public Boolean isComposite() {
        return true;
    }

    @Override
    public Integer getNumberOfGuns() {
        return gunComponents.stream().mapToInt(GunComponent::getNumberOfGuns).sum();
    }

    @Override
    public BigDecimal getTotalPrice() {
        return gunComponents.stream().map(GunComponent::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Stream<Accesory> getAccesories() {
        return gunComponents.stream().flatMap(GunComponent::getAccesories);
    }

    @Override
    public Stream<CompatibleAmmo> getCompatibleAmmos() {
        return gunComponents.stream().flatMap(GunComponent::getCompatibleAmmos);
    }
}
