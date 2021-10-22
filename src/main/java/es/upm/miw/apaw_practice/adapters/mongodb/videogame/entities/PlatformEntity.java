package es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities;

import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class PlatformEntity {

    @Id
    private String id;
    @Indexed(unique = true)

    private String consoleName;
    private String model;
    private String memory;

    public PlatformEntity() {
        //empty from framework
    }

    public PlatformEntity(Platform platform) {
        BeanUtils.copyProperties(platform, this);
        this.id = UUID.randomUUID().toString();
    }

    public PlatformEntity(String consoleName, String model, String memory) {
        this.id = UUID.randomUUID().toString();
        this.consoleName = consoleName;
        this.model = model;
        this.memory = memory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConsoleName() {
        return consoleName;
    }

    public void setConsoleName(String consoleName) {
        this.consoleName = consoleName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public void fromPlatform(Platform platform) {
        BeanUtils.copyProperties(platform, this);
    }

    public Platform toPlatform() {
        Platform platform = new Platform();
        BeanUtils.copyProperties(this, platform);
        return platform;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (consoleName.equals(((PlatformEntity) obj).consoleName));
    }

    @Override
    public int hashCode() {
        return consoleName.hashCode();
    }

    @Override
    public String toString() {
        return "PlatformEntity{" +
                "id='" + id + '\'' +
                ", consoleName='" + consoleName + '\'' +
                ", model='" + model + '\'' +
                ", memory='" + memory + '\'' +
                '}';
    }
}
