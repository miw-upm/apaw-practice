package es.upm.miw.apaw_practice.domain.models.pharmacy;

import java.util.List;
import java.util.Objects;

public class ActiveIngredient {

    private String code;
    private Drug drug;
    private List<String> components;
    private Integer dose;


    public ActiveIngredient() {
        //empty from framework
    }

    public ActiveIngredient(String code, Drug drug, List<String> components, Integer dose) {
        this.code = code;
        this.drug = drug;
        this.components = components;
        this.dose = dose;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActiveIngredient that = (ActiveIngredient) o;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "ActiveIngredient{" +
                "code='" + code + '\'' +
                ", drug=" + drug +
                ", components=" + components +
                ", dose=" + dose +
                '}';
    }
}
