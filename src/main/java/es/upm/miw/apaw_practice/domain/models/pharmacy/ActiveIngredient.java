package es.upm.miw.apaw_practice.domain.models.pharmacy;

import java.math.BigDecimal;
import java.util.List;

public class ActiveIngredient {

    private String reference;
    private List<String> components;
    private BigDecimal eurosGram;

    public ActiveIngredient(String reference, List<String> components, BigDecimal eurosGram) {
        this.reference = reference;
        this.components = components;
        this.eurosGram = eurosGram;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    public BigDecimal getEurosGram() {
        return eurosGram;
    }

    public void setEurosGram(BigDecimal eurosGram) {
        this.eurosGram = eurosGram;
    }
}
