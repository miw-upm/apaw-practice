package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities;

import es.upm.miw.apaw_practice.domain.models.restaurant.Waiter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Document
public class WaiterEntity {
    @Id
    private String id;
    private String section;
    private String category;

    public WaiterEntity(){
        //empty for framework
    }

    public WaiterEntity(String section, String category) {
        this.id = UUID.randomUUID().toString();
        this.section = section;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((WaiterEntity) obj).id));
    }

    @Override
    public String toString() {
        return "WaiterEntity{" +
                "id='" + id + '\'' +
                ", section='" + section + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
