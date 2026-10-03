package es.upm.miw.apaw.adapters.out.courthearing.postgres;

import es.upm.miw.apaw.domain.model.courthearing.Court;
import es.upm.miw.apaw.domain.model.courthearing.CourtType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CourtEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(unique = true)
    private String phone;

    private LocalTime openingTime;

    private LocalTime closingTime;

    @Enumerated(EnumType.STRING)
    private CourtType type;

        public CourtEntity(Court court) {
        BeanUtils.copyProperties(court, this);
    }

    public Court toDomain() {
        Court court = new Court();
        BeanUtils.copyProperties(this, court);
        return court;
    }
}