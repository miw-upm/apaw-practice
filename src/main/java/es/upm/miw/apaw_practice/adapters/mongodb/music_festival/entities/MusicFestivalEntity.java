package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities;

import es.upm.miw.apaw_practice.domain.models.music_festival.MusicFestival;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "music_festivals")
public class MusicFestivalEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private LocalDateTime creationDate;
    private BigDecimal budget;
    private List<ConcertEntity> concerts;

    public MusicFestivalEntity() {
        // empty for framework
    }

    public MusicFestivalEntity(String name, LocalDateTime creationDate, BigDecimal budget, List<ConcertEntity> concerts) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.creationDate = creationDate;
        this.budget = budget;
        this.concerts = concerts;
    }

    public MusicFestivalEntity(MusicFestival musicFestival) {
        this.name = musicFestival.getName();
        this.creationDate = musicFestival.getCreationDate();
        this.budget = musicFestival.getBudget();
    }

    public MusicFestival toDomain() {
        MusicFestival musicFestival = new MusicFestival();
        BeanUtils.copyProperties(this, musicFestival, "concerts");
        musicFestival.setConcerts(this.concerts != null
                ? this.concerts.stream().map(ConcertEntity::toDomain).toList()
                : null);
        return musicFestival;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public List<ConcertEntity> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<ConcertEntity> concerts) {
        this.concerts = concerts;
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
        if (!(object instanceof MusicFestivalEntity other)){
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "MusicFestivalEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", budget=" + budget +
                ", concerts=" + concerts +
                '}';
    }
}
