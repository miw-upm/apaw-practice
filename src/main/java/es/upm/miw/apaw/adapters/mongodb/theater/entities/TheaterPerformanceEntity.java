package es.upm.miw.apaw.adapters.mongodb.theater.entities;

import es.upm.miw.apaw.domain.models.theater.TheaterArtist;
import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import es.upm.miw.apaw.domain.models.theater.TheaterPerformance;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "theater_performances")
public class TheaterPerformanceEntity {
    @Id
    private UUID id;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String performanceCode;

    private String performanceTitle;
    private LocalDate performanceDate;
    private Integer performanceDurationMinutes;
    private BigDecimal performanceTicketPrice;

    @DBRef
    private TheaterHallEntity performanceHall;

    @DBRef
    private Set<TheaterArtistEntity> performanceArtists;

    public TheaterPerformanceEntity(TheaterPerformance theaterPerformance) {
        BeanUtils.copyProperties(theaterPerformance, this, "performanceHall", "performanceArtists");
        this.id = UUID.randomUUID();
    }

    public void fromTheaterPerformance(TheaterPerformance theaterPerformance) {
        BeanUtils.copyProperties(theaterPerformance, this, "performanceHall", "performanceArtists");
    }

    public TheaterPerformance toTheaterPerformance() {
        TheaterPerformance theaterPerformance = new TheaterPerformance();
        BeanUtils.copyProperties(this, theaterPerformance, "performanceHall", "performanceArtists");
        if (this.performanceHall != null) {
            theaterPerformance.setPerformanceHall(this.performanceHall.toTheaterHall());
        }
        if (this.performanceArtists != null) {
            theaterPerformance.setPerformanceArtists(this.performanceArtists.stream()
                    .map(TheaterArtistEntity::toTheaterArtist)
                    .collect(Collectors.toSet()));
        }
        return theaterPerformance;
    }
}
