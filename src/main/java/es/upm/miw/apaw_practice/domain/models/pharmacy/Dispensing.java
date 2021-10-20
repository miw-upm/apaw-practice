package es.upm.miw.apaw_practice.domain.models.pharmacy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Dispensing {

    private String id;
    private List<ActiveIngredient> activeIngredients;
    private LocalDateTime dispensingTimestamp;

    public Dispensing() {
        //empty for framework
    }

    public Dispensing(List<ActiveIngredient> activeIngredients, LocalDateTime dispensingTimestamp) {
        this.activeIngredients = activeIngredients;
        this.dispensingTimestamp = dispensingTimestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ActiveIngredient> getActiveIngredients() {
        return activeIngredients;
    }

    public void setActiveIngredients(List<ActiveIngredient> activeIngredients) {
        this.activeIngredients = activeIngredients;
    }

    public LocalDateTime getDispensingTimestamp() {
        return dispensingTimestamp;
    }

    public void setDispensingTimestamp(LocalDateTime dispensingTimestamp) {
        this.dispensingTimestamp = dispensingTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dispensing that = (Dispensing) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Dispensing{" +
                "id='" + id + '\'' +
                ", activeIngredients=" + activeIngredients +
                ", dispensingTimestamp=" + dispensingTimestamp +
                '}';
    }
}
