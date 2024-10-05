package es.upm.miw.apaw_practice.domain.models.competition;

import java.time.LocalDateTime;

public class Organization {

    private String nameOrganization;
    private LocalDateTime creationDateOrganization;
    private boolean international;

    public Organization() {
        // empty for framework
    }

    public Organization(String nameOrganization, LocalDateTime creationDateOrganization, boolean isInternational) {
        this.nameOrganization = nameOrganization;
        this.creationDateOrganization = creationDateOrganization;
        this.international = isInternational;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public void setNameOrganization(String nameOrganization) {
        this.nameOrganization = nameOrganization;
    }

    public LocalDateTime getCreationDateOrganization() {
        return creationDateOrganization;
    }

    public void setCreationDateOrganization(LocalDateTime creationDateOrganization) {
        this.creationDateOrganization = creationDateOrganization;
    }

    public boolean isInternational() {
        return international;
    }

    public void setInternational(boolean international) {
        this.international = international;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "nameOrganization='" + nameOrganization + '\'' +
                ", creationDateOrganization=" + creationDateOrganization +
                ", isInternational=" + international +
                '}';
    }
}
