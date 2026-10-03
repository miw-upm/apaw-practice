package es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres;

import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDay;
import es.upm.miw.apaw.domain.model.deadlinecalculator.ScopeLevel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = {"date", "scope_level", "region", "city"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NonWorkingDayEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScopeLevel scopeLevel;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Boolean recurring;

    public NonWorkingDayEntity(NonWorkingDay nonWorkingDay) {
        BeanUtils.copyProperties(nonWorkingDay, this, "region", "city");
        this.region = orEmpty(nonWorkingDay.getRegion());
        this.city = orEmpty(nonWorkingDay.getCity());
    }

    public NonWorkingDay toDomain() {
        NonWorkingDay nonWorkingDay = new NonWorkingDay();
        BeanUtils.copyProperties(this, nonWorkingDay, "region", "city");
        nonWorkingDay.setRegion(this.region.isEmpty() ? null : this.region);
        nonWorkingDay.setCity(this.city.isEmpty() ? null : this.city);
        return nonWorkingDay;
    }

    private static String orEmpty(String value) {
        return value == null || value.isBlank() ? "" : value;
    }
}
