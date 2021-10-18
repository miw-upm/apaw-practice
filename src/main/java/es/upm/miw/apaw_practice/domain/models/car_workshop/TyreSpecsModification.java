package es.upm.miw.apaw_practice.domain.models.car_workshop;

import java.util.Objects;

public class TyreSpecsModification {

    private String oldSpec;
    private String newSpec;

    public TyreSpecsModification(){
        //empty for framework
    }

    public TyreSpecsModification(String oldSpec, String newSpec){
        this.oldSpec = oldSpec;
        this.newSpec = newSpec;
    }

    public String getOldSpec() {
        return this.oldSpec;
    }

    public void setOldSpec(String oldSpec) {
        this.oldSpec = oldSpec;
    }

    public String getNewSpec() {
        return this.newSpec;
    }

    public void setNewSpec(String newSpec) {
        this.newSpec = newSpec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TyreSpecsModification that = (TyreSpecsModification) o;
        return Objects.equals(this.oldSpec, that.oldSpec) && Objects.equals(this.newSpec, that.newSpec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.oldSpec, this.newSpec);
    }

    @Override
    public String toString() {
        return "TyreSpecsModification{" +
                "oldSpec='" + this.oldSpec + '\'' +
                ", newSpec='" + this.newSpec + '\'' +
                '}';
    }
}
