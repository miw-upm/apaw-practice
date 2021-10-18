package es.upm.miw.apaw_practice.domain.models.pharmacy;

import java.util.List;

public class ActiveIngredient {

    private Drug drug;
    private List<String> components;
    private Integer dose;

    public ActiveIngredient(Drug drug,List<String> components, Integer dose) {
        this.drug = drug;
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

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    @Override
    public String toString() {
        return "ActiveIngredient{" +
                "components=" + components +
                ", dose=" + dose +
                ", drug=" + drug +
                '}';
    }
}
