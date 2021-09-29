package es.upm.miw.apaw_practice.domain.models.car_workshop;

import java.util.Objects;

public class TyreSpecification {
    private int width;
    private int diameter;
    private int weightIndex;
    private String speedRating;

    public TyreSpecification(){
        //empty for framework
    }

    public TyreSpecification(int width, int diameter, int weightIndex, String speedRating){
        this.width = width;
        this.diameter = diameter;
        this.weightIndex = weightIndex;
        this.speedRating = speedRating;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getWeightIndex() {
        return weightIndex;
    }

    public void setWeightIndex(int weightIndex) {
        this.weightIndex = weightIndex;
    }

    public String getSpeedRating() {
        return speedRating;
    }

    public void setSpeedRating(String speedRating) {
        this.speedRating = speedRating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.width, this.diameter, this.weightIndex, this.speedRating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TyreSpecification tyreSpec = (TyreSpecification) o;
        return this.width == tyreSpec.width &&
                this.diameter == tyreSpec.diameter &&
                this.weightIndex == tyreSpec.weightIndex &&
                Objects.equals(this.speedRating, tyreSpec.speedRating);
    }

    @Override
    public String toString() {
        return "TyreSpecification{" +
                "width=" + this.width +
                ", diameter=" + this.diameter +
                ", weightIndex=" + this.weightIndex +
                ", speedRating='" + this.speedRating + '\'' +
                '}';
    }
}
