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

    private String region;

    private String city;

    @Column(nullable = false)
    private Boolean recurring;

    public NonWorkingDayEntity(NonWorkingDay nonWorkingDay) {
        BeanUtils.copyProperties(nonWorkingDay, this);
    }

    public NonWorkingDay toDomain() {
        NonWorkingDay nonWorkingDay = new NonWorkingDay();
        BeanUtils.copyProperties(this, nonWorkingDay);
        return nonWorkingDay;
    }
}
