package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class ShopEntity {
    @Id
    private String id;
    private String name;
    private String address;
    @DBRef
    private List<EmployeeEntity> employees;
    @DBRef
    private List<ProviderEntity> providers;

    public ShopEntity() {
        //empty from framework
    }

    public ShopEntity(String name, String address, List<EmployeeEntity> employees, List<ProviderEntity> providers) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.employees = employees;
        this.providers = providers;
    }

    public String getId() {
        return id;
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

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public List<ProviderEntity> getProviders() {
        return providers;
    }

    public void setProviders(List<ProviderEntity> providers) {
        this.providers = providers;
    }

    @Override
    public String toString() {
        return "ShopEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", employees=" + employees +
                ", providers=" + providers +
                '}';
    }
}
