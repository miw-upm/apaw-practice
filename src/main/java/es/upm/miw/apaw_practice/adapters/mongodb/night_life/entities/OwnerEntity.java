package es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities;

import es.upm.miw.apaw_practice.domain.models.night_life.Owner;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Document
public class OwnerEntity {
    @Id
    private String id;
    private String name;
    private String phone;
    private String email;

    public OwnerEntity() {
        //empty for framework
    }

    public OwnerEntity(String name, String phone, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((OwnerEntity) obj).id));
    }

    @Override
    public String toString() {
        return "Owner{" + "name=" + name + ", phone=" + phone + ", email=" + email + '}';
    }

    public Owner toOwner() {
        Owner owner = new Owner();
        BeanUtils.copyProperties(this, owner);
        return owner;
    }
}
