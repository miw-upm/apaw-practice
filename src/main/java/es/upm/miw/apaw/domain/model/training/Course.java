package es.upm.miw.apaw.domain.model.training;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Course {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String name;

    private String certificateReference;

    @NotNull
    private Integer durationHours;

    private Boolean online;

    private LocalDate launchDate;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.online == null) {
            this.online = true;
        }
    }
}
