package es.upm.miw.apaw_practice.domain.models.department;

import java.util.List;

public class Company {
    private String direction;
    private String cif;

    public Company() {
        //empty for framework
    }

    public Company(String direction, String cif) {
        this.direction = direction;
        this.cif = cif;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    @Override
    public String toString() {
        return "Company{" +
                "direction='" + direction + '\'' +
                ", cif='" + cif + '\'' +
                '}';
    }
}
