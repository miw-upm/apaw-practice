package es.upm.miw.apaw.adapters.mongodb.theater.entities;

import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import es.upm.miw.apaw.domain.models.theater.TheaterVenue;
import es.upm.miw.apaw.domain.models.theater.TheaterPerformance;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "theater_venues")
public class TheaterVenueEntity {
    @Id
    private UUID id;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String venueCode;

    private String venueName;
    private String venueCity;
    private Boolean venueOpen;
    private LocalDateTime venueCreatedAt;

    @DBRef
    private List<TheaterHallEntity> venueHalls;

    private UUID venueManagerId;

    public TheaterVenueEntity(TheaterVenue theaterVenue) {
        BeanUtils.copyProperties(theaterVenue, this, "venueHalls", "venueManager");
        this.id = UUID.randomUUID();
        if (theaterVenue.getVenueManager() != null) {
            this.venueManagerId = theaterVenue.getVenueManager().getId();
        }
    }

    public void fromTheaterVenue(TheaterVenue theaterVenue) {
        BeanUtils.copyProperties(theaterVenue, this, "venueHalls", "venueManager");
        if (theaterVenue.getVenueManager() != null) {
            this.venueManagerId = theaterVenue.getVenueManager().getId();
        }
    }

    public TheaterVenue toTheaterVenue() {
        TheaterVenue theaterVenue = new TheaterVenue();
        BeanUtils.copyProperties(this, theaterVenue, "venueHalls");
        if (this.venueManagerId != null) {
            theaterVenue.setVenueManager(
                    es.upm.miw.apaw.domain.models.UserDto.builder().id(venueManagerId).build()
            );
        }
        if (this.venueHalls != null) {
            theaterVenue.setVenueHalls(this.venueHalls.stream()
                    .map(TheaterHallEntity::toTheaterHall)
                    .toList());
        }
        return theaterVenue;
    }
}
