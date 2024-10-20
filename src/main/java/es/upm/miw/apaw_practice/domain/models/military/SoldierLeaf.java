package es.upm.miw.apaw_practice.domain.models.military;

import java.time.LocalDate;

public class SoldierLeaf implements SoldierComponent {
    private final Soldier soldier;

    public SoldierLeaf(Soldier soldier) {
        this.soldier = soldier;
    }

    @Override
    public String getIdentityDocument() {
        return this.soldier.getIdentityDocument();
    }

    @Override
    public String getFullName() {
        return this.soldier.getFullName();
    }

    @Override
    public String getRank() {
        return this.soldier.getRank();
    }

    @Override
    public LocalDate getBirthDate() {
        return this.soldier.getBirthDate();
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(SoldierComponent soldierComponent) {
        // Do nothing because is a leaf
    }

    @Override
    public void remove(SoldierComponent soldierComponent) {
        // Do nothing because is a leaf
    }
}
