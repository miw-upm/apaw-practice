package es.upm.miw.apaw_practice.domain.models.hospital;

public class DiseaseUpdate {

    private String alias;
    private String description;

    public DiseaseUpdate() {
        //empty for framework
    }

    public DiseaseUpdate(String alias, String description) {
        this.alias = alias;
        this.description = description;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DiseaseUpdate{" +
                "alias='" + this.alias + '\'' +
                ", description='" + this.description + '\'' +
                '}';
    }
}
