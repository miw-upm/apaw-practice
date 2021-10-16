package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities;

import es.upm.miw.apaw_practice.domain.models.emarketer.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class CustomerEntity {

    @Id
    private String id;
    private String name;
    private String address;
    private String type;

    public CustomerEntity() {
        //empty for framework
    }

    public CustomerEntity(String name, String address, String type) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Customer toCustomer() {
        Customer customer = new Customer();
        BeanUtils.copyProperties(this, customer);
        customer.setName(this.name);
        customer.setAddress(this.address);
        customer.setType(this.type);
        return customer;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((CustomerEntity) obj).id));
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", type=" + type +
                '}';
    }
}
