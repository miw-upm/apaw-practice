package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class EmarketerEntity {

    @Id
    private String id;
    private String name;
    private String address;
    private Boolean systemic;
    @DBRef
    private List<CupsEntity> cupsEntities;
    @DBRef
    private List<PlanEntity> planEntities;

    public EmarketerEntity() {
        // empty for framework
    }

    public EmarketerEntity(String name, String address, Boolean systemic, List<CupsEntity> cupsEntities, List<PlanEntity> planEntities) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.systemic = systemic;
        this.cupsEntities = cupsEntities;
        this.planEntities = planEntities;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean isSystemic() {
        return this.systemic;
    }

    public void setSystemic(Boolean systemic) {
        this.systemic = systemic;
    }

    public List<CupsEntity> getCupsEntities() {
        return cupsEntities;
    }

    public void setCupsEntities(List<CupsEntity> cupsEntities) {
        this.cupsEntities = cupsEntities;
    }

    public List<PlanEntity> getPlanEntities() {
        return this.planEntities;
    }

    public void setPlanEntities(List<PlanEntity> planEntities) {
        this.planEntities = planEntities;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((EmarketerEntity) obj).id));
    }

    @Override
    public String toString() {
        return "EmarketerEntity{" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", address=" + this.address +
                ", systemic=" + this.systemic +
                '}';
    }

}
