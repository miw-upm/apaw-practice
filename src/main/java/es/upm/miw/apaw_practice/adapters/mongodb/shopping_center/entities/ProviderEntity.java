package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Provider;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class ProviderEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String mainService;
    private boolean isNational;

    public ProviderEntity() {
        //empty from framework
    }

    public ProviderEntity(Provider provider) {
        BeanUtils.copyProperties(provider, this);
        this.id = UUID.randomUUID().toString();
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

    public String getMainService() {
        return mainService;
    }

    public void setMainService(String mainService) {
        this.mainService = mainService;
    }

    public boolean isNational() {
        return isNational;
    }

    public void setNational(boolean national) {
        isNational = national;
    }

    public Provider toProvider() {
        Provider provider = new Provider();
        BeanUtils.copyProperties(this, provider);
        return provider;
    }

    @Override
    public String toString() {
        return "ProviderEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mainService='" + mainService + '\'' +
                ", isNational=" + isNational +
                '}';
    }
}
