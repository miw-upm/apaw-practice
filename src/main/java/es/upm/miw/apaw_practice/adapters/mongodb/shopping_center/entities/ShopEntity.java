package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Employee;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Provider;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Shop;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class ShopEntity {
    @Id
    private String id;
    private String name;
    private String address;
    @DBRef
    private List<EmployeeShoppingCenterEntity> employees;
    @DBRef
    private List<ProviderEntity> providers;

    public ShopEntity() {
        //empty from framework
    }

    public ShopEntity(String name, String address, List<EmployeeShoppingCenterEntity> employees, List<ProviderEntity> providers) {
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

    public List<EmployeeShoppingCenterEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeShoppingCenterEntity> employees) {
        this.employees = employees;
    }

    public List<ProviderEntity> getProviders() {
        return providers;
    }

    public void setProviders(List<ProviderEntity> providers) {
        this.providers = providers;
    }

    public Shop toShop() {
        Shop shop = new Shop();
        BeanUtils.copyProperties(this, shop, "employees", "providers");
        List<Employee> employees = this.employees.stream()
                .map(EmployeeShoppingCenterEntity::toEmployee)
                .collect(Collectors.toList());
        shop.setEmployees(employees);
        List<Provider> providers = this.providers.stream()
                .map(ProviderEntity::toProvider)
                .collect(Collectors.toList());
        shop.setProviders(providers);
        return shop;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((ShopEntity) obj).id));
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
