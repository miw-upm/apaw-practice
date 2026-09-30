package es.upm.miw.apaw.adapters.out.training.postgres;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CourseEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column
    private String name;

    @Column
    private String certificateReference;

    @Column
    private Integer durationHours;

    @Column
    private Boolean online;

    @Column
    private LocalDate launchDate;
}
