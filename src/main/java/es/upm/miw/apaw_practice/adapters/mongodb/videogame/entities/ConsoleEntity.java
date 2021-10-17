package es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class ConsoleEntity {

    @Id
    private String id;
    @Indexed(unique = true)

    private String consoleName;
    private String model;
    private String color;
    private String memory;

    public ConsoleEntity(String consoleName, String model, String color, String memory) {
        this.id = UUID.randomUUID().toString();
        this.consoleName = consoleName;
        this.model = model;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "ConsoleEntity{" +
                "id='" + id + '\'' +
                ", consoleName='" + consoleName + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", memory='" + memory + '\'' +
                '}';
    }
}
