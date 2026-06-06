package es.upm.miw.apaw.adapters.mongodb.theater.entities;

import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "theater_halls")
public class TheaterHallEntity {
    @Id
    private UUID id;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String hallCode;

    private String hallName;
    private Integer hallCapacity;
    private Boolean hallAccessible;

    public TheaterHallEntity(TheaterHall theaterHall) {
        BeanUtils.copyProperties(theaterHall, this);
        this.id = UUID.randomUUID();
    }

    public void fromTheaterHall(TheaterHall theaterHall) {
        BeanUtils.copyProperties(theaterHall, this);
    }

    public TheaterHall toTheaterHall() {
        TheaterHall theaterHall = new TheaterHall();
        BeanUtils.copyProperties(this, theaterHall);
        return theaterHall;
    }
}
