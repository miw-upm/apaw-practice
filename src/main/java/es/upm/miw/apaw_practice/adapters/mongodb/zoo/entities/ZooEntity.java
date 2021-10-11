package es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities;

import es.upm.miw.apaw_practice.domain.models.zoo.Zoo;
import es.upm.miw.apaw_practice.domain.models.zoo.ZooAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class ZooEntity {

    @Id
    private String id;
    private ZooAddress address;
    private Integer phoneNumber;

    public ZooEntity() {
        //empty from framework
    }

    public ZooEntity(Zoo zoo) {
        BeanUtils.copyProperties(zoo, this);
    }

    public Zoo toZoo() {
        return new Zoo(this.id, this.address, this.phoneNumber);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZooAddress getAddress() {
        return address;
    }

    public void setAddress(ZooAddress address) {
        this.address = address;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || obj != null
                && getClass() == obj.getClass()
                && (id.equals(((ZooEntity) obj).id));
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "id=" + id +
                ", " + address.toString() +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
