package es.upm.miw.apaw_practice.domain.models.military;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SoldierComposite implements SoldierComponent {
    private final List<SoldierComponent> soldiers;

    public SoldierComposite() {
        this.soldiers = new ArrayList<>();
    }

    public SoldierComposite(List<SoldierComponent> soldiers) {
        this.soldiers = soldiers;
    }

    @Override
    public void add(SoldierComponent soldierComponent) {
        this.soldiers.add(soldierComponent);
    }

    @Override
    public void remove(SoldierComponent soldierComponent) {
        this.soldiers.remove(soldierComponent);
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public String getIdentityDocument() {
        return this.soldiers.stream()
                .map(SoldierComponent::getIdentityDocument)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String getFullName() {
        return this.soldiers.stream()
                .map(SoldierComponent::getFullName)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String getRank() {
        return this.soldiers.stream()
                .map(SoldierComponent::getRank)
                .distinct()
                .collect(Collectors.joining(", "));
    }

    @Override
    public LocalDate getBirthDate() {
        return this.soldiers.stream()
                .map(SoldierComponent::getBirthDate)
                .filter(Objects::nonNull)
                .min(LocalDate::compareTo)
                .orElse(null);
    }
}
