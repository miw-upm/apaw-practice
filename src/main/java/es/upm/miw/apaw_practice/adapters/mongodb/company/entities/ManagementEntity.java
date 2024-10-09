package es.upm.miw.apaw_practice.adapters.mongodb.company.entities;

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
    private String position;

    public ManagementEntity() {
        // Empty for framework
    }

    public ManagementEntity(String name, String position) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // equals, hashCode, toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagementEntity that = (ManagementEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ManagementEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
