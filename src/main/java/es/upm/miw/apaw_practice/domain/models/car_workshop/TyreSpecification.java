package es.upm.miw.apaw_practice.domain.models.car_workshop;

import java.util.List;
import java.util.Objects;

public class TyreSpecification {
    private Integer width;
    private Integer diameter;
    private String loadSpeedIndex;
    private List<Tyre> tyres;

    public TyreSpecification() {
        //empty for framework
    }

    public TyreSpecification(Integer width, Integer diameter, String loadSpeedIndex, List<Tyre> tyres) {
        this.width = width;
        this.diameter = diameter;
        this.loadSpeedIndex = loadSpeedIndex;
        this.tyres = tyres;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

    public String getLoadSpeedIndex() {
        return loadSpeedIndex;
    }

    public void setLoadSpeedIndex(String loadSpeedIndex) {
        this.loadSpeedIndex = loadSpeedIndex;
    }

    public List<Tyre> getTyres() {
        return tyres;
    }

    public void setTyres(List<Tyre> tyres) {
        this.tyres = tyres;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.width, this.diameter, this.loadSpeedIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TyreSpecification tyreSpec = (TyreSpecification) o;
        return Objects.equals(this.width, tyreSpec.width) &&
                Objects.equals(this.diameter, tyreSpec.diameter) &&
                Objects.equals(this.loadSpeedIndex, tyreSpec.loadSpeedIndex);
    }

    @Override
    public String toString() {
        return "TyreSpecification{" +
                "width=" + this.width +
                ", diameter=" + this.diameter +
                ", loadSpeedIndex='" + this.loadSpeedIndex + '\'' +
                ", tyres=" + this.tyres.toString() +
                '}';
    }
}
