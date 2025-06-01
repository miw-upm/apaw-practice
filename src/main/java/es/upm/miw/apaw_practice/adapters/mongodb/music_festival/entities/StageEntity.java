package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import es.upm.miw.apaw_practice.domain.models.music_festival.Stage;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "stages")
public class StageEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String location;
    private int capacity;
    private LocalDateTime openTime;

    public StageEntity() {
        // empty for framework
    }

    public StageEntity(String name, String location, int capacity, LocalDateTime openTime) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.openTime = openTime;
    }

    public Stage toDomain() {
        Stage stage = new Stage();
        BeanUtils.copyProperties(this, stage);
        return stage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof StageEntity other)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "StageEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", openTime=" + openTime +
                '}';
    }
}
