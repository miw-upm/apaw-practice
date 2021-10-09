package es.upm.miw.apaw_practice.domain.models.zoo;

import java.time.LocalDate;

public class CageFumigation {

    private LocalDate oldFumigation;
    private LocalDate newFumigation;

    public CageFumigation() {
        //empty from framework
    }

    public CageFumigation(LocalDate oldFumigation, LocalDate newFumigation) {
        this.oldFumigation = oldFumigation;
        this.newFumigation = newFumigation;
    }

    public LocalDate getOldFumigation() {
        return oldFumigation;
    }

    public void setOldFumigation(LocalDate oldFumigation) {
        this.oldFumigation = oldFumigation;
    }

    public LocalDate getNewFumigation() {
        return newFumigation;
    }

    public void setNewFumigation(LocalDate newFumigation) {
        this.newFumigation = newFumigation;
    }

    @Override
    public String toString() {
        return "CageFumigation{" +
                "oldFumigation=" + oldFumigation +
                ", newFumigation=" + newFumigation +
                '}';
    }
}
