package es.upm.miw.apaw.adapters.mongodb.theater.entities;

import es.upm.miw.apaw.domain.models.theater.TheaterArtist;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "theater_artists")
public class TheaterArtistEntity {
    @Id
    private UUID id;

    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String artistCode;

    private String artistFullName;
    private LocalDate artistBirthDate;
    private BigDecimal artistFee;
    private Boolean artistActive;

    public TheaterArtistEntity(TheaterArtist theaterArtist) {
        BeanUtils.copyProperties(theaterArtist, this);
        this.id = UUID.randomUUID();
    }

    public void fromTheaterArtist(TheaterArtist theaterArtist) {
        BeanUtils.copyProperties(theaterArtist, this);
    }

    public TheaterArtist toTheaterArtist() {
        TheaterArtist theaterArtist = new TheaterArtist();
        BeanUtils.copyProperties(this, theaterArtist);
        return theaterArtist;
    }

    public void patchActive(Boolean artistActive) {
        this.artistActive = artistActive;
    }
}
