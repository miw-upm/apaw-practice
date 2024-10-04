package es.upm.miw.apaw_practice.domain.models.military;

import java.time.LocalDate;
import java.util.List;

public class Mission {
    private String codeName;
    private Boolean international;
    private LocalDate startDate;
    private Unit unit;
    private List<Weapon> weapons;

    public Mission() {
        //empty for framework
    }

    public Mission(String codeName, Boolean international, LocalDate startDate, Unit unit, List<Weapon> weapons) {
        this.codeName = codeName;
        this.international = international;
        this.startDate = startDate;
        this.unit = unit;
        this.weapons = weapons;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Boolean getInternational() {
        return international;
    }

    public void setInternational(Boolean international) {
        this.international = international;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    @Override
    public int hashCode() {
        return this.codeName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (codeName.equals(((Mission) obj).codeName));
    }

    @Override
    public String toString() {
        return "Mission{" +
                "codeName='" + codeName + '\'' +
                ", international=" + international +
                ", startDate=" + startDate +
                ", unit=" + unit +
                ", weapons=" + weapons.stream().map(Weapon::getSerialCode).toList() +
                '}';
    }
}
