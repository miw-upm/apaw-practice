package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.OwnerClinic;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class OwnerClinicEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String address;
    private String phone;

    public OwnerClinicEntity() {
        //empty from framework
    }

    public OwnerClinicEntity(OwnerClinic ownerClinic) {
        BeanUtils.copyProperties(ownerClinic, this);
        this.id = UUID.randomUUID().toString();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void fromOwner(OwnerClinic ownerClinic) {
        BeanUtils.copyProperties(ownerClinic, this);
    }

    public OwnerClinic toOwner() {
        OwnerClinic ownerClinic = new OwnerClinic();
        BeanUtils.copyProperties(this, ownerClinic);
        return ownerClinic;
    }

    @Override
    public boolean equals(Object object) {
        return this == object || object != null && getClass() == object.getClass() &&
                (name.equals(((OwnerClinicEntity) object).name));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "OwnerClinicEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}