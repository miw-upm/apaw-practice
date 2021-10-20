package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities;

import es.upm.miw.apaw_practice.domain.models.pharmacy.ActiveIngredient;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class ActiveIngredientEntity {
    @DBRef
    private DrugEntity drugEntity;
    private List<String> components;
    private Integer dose;

    public ActiveIngredientEntity() {
        //empty for framework
    }

    public ActiveIngredientEntity(DrugEntity drugEntity, List<String> components, Integer dose) {
        this.drugEntity = drugEntity;
        this.components = components;
        this.dose = dose;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    public Integer getDose() {
        return dose;
    }

    public void setDose(Integer dose) {
        this.dose = dose;
    }

    public DrugEntity getDrugEntity() {
        return drugEntity;
    }

    public void setDrugEntity(DrugEntity drugEntity) {
        this.drugEntity = drugEntity;
    }

    public ActiveIngredient toActiveIngredient() {
        return new ActiveIngredient(this.drugEntity.toDrug(), this.components, this.dose);
    }

    @Override
    public String toString() {
        return "ActiveIngredientEntity{" +
                "drugEntity=" + drugEntity +
                ", components=" + components +
                ", dose=" + dose +
                '}';
    }
}
