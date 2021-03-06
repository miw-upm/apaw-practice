package es.upm.miw.apaw_practice.domain.models.tree_conservation;

import java.time.LocalDateTime;

public class Inspection {
    private String id;
    private LocalDateTime date;
    private String type;
    private String treeStatus;
    private String dni;

    public Inspection() {
        //empty for framework
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTreeStatus() {
        return treeStatus;
    }

    public void setTreeStatus(String treeStatus) {
        this.treeStatus = treeStatus;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Inspection{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", treeStatus='" + treeStatus + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}
