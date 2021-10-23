package es.upm.miw.apaw_practice.domain.models.hospital;

public class Disease {

    private String description;
    private Boolean severe;
    private String alias;

    public Disease() {
        //empty for framework
    }

    public Disease(String description, Boolean severe, String alias) {
        this.description = description;
        this.severe = severe;
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSevere() {
        return severe;
    }

    public void setSevere(Boolean severe) {
        this.severe = severe;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "description='" + this.description + '\'' +
                ", severe=" + this.severe +
                ", alias='" + this.alias + '\'' +
                '}';
    }
}
