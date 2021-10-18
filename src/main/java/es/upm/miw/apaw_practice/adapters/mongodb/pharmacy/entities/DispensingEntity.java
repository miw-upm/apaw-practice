package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Dispensing;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class DispensingEntity {

    @Id
    private String id;
    private LocalDateTime dispensingTimestamp;
    private List<ActiveIngredientEntity> activeIngredientEntities;

    public DispensingEntity() {
        //empty from framework
    }

    public DispensingEntity(Dispensing dispensing) {
        BeanUtils.copyProperties(dispensing, this);
        this.id = UUID.randomUUID().toString();
    }

    public DispensingEntity(LocalDateTime dispensingTimestamp, List<ActiveIngredientEntity> activeIngredientEntities) {
        this.id = UUID.randomUUID().toString();
        this.dispensingTimestamp = dispensingTimestamp;
        this.activeIngredientEntities = activeIngredientEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDispensingTimestamp() {
        return dispensingTimestamp;
    }

    public void setDispensingTimestamp(LocalDateTime dispensingTimestamp) {
        this.dispensingTimestamp = dispensingTimestamp;
    }

    public List<ActiveIngredientEntity> getActiveIngredientEntities() {
        return activeIngredientEntities;
    }

    public void setActiveIngredientEntities(List<ActiveIngredientEntity> activeIngredientEntities) {
        this.activeIngredientEntities = activeIngredientEntities;
    }

    public Dispensing toDispensing() {
        Dispensing dispensing = new Dispensing();
        BeanUtils.copyProperties(this, dispensing);
        return dispensing;
    }

    public void fromDrug(Drug drug) {
        BeanUtils.copyProperties(drug, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DispensingEntity that = (DispensingEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DispensingEntity{" +
                "id='" + id + '\'' +
                ", dispensingTimestamp=" + dispensingTimestamp +
                ", activeIngredientEntities=" + activeIngredientEntities +
                '}';
    }
}
