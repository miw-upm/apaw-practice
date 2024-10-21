package es.upm.miw.apaw_practice.domain.models.military;

import java.time.LocalDate;

public interface SoldierComponent {
    String getIdentityDocument();

    String getFullName();

    String getRank();

    LocalDate getBirthDate();

    boolean isComposite();

    void add(SoldierComponent soldierComponent);

    void remove(SoldierComponent soldierComponent);
}
