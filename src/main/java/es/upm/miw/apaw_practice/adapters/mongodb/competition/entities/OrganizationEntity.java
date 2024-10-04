package es.upm.miw.apaw_practice.adapters.mongodb.competition.entities;

import es.upm.miw.apaw_practice.domain.models.competition.Organization;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class OrganizationEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String nameOrganization;
    private LocalDateTime creationDateOrganization;
    private boolean international;

    public OrganizationEntity() {
        // empty for framework
    }

    public OrganizationEntity(Organization organization) {
        BeanUtils.copyProperties(organization, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "CompetitionEntity{" +
                "id='" + id + '\'' +
                ", nameOrganization='" + nameOrganization + '\'' +
                ", creationDateOrganization=" + creationDateOrganization +
                ", international=" + international +
                '}';
    }
}
