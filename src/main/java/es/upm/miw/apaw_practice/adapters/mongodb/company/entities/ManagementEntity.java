package es.upm.miw.apaw_practice.adapters.mongodb.company.entities;

import es.upm.miw.apaw_practice.domain.models.bank.BankAccount;
import es.upm.miw.apaw_practice.domain.models.company.Management;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;
import java.util.UUID;

@Document
public class ManagementEntity {

    @Id
    private String id;

    @Field
    private String name;

    @Field
    private boolean activated;

    public ManagementEntity() {
        // Empty for framework
    }

    public ManagementEntity(String name, boolean activated) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.activated = activated;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Management toManagement() {
        return new Management(UUID.randomUUID().toString(),name, activated);
    }


    // equals, hashCode, toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagementEntity that = (ManagementEntity) o;
        return activated == that.activated &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, activated);
    }

    @Override
    public String toString() {
        return "ManagementEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", activated=" + activated +
                '}';
    }
}
