package es.upm.miw.apaw.domain.model.courthearing;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Court {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    private String phone;

    private LocalTime openingTime;

    private LocalTime closingTime;

    private CourtType courtType;

    private List<CourtHearing> courtHearings;

    public void doDefault() {
        this.id = UUID.randomUUID();
    }
}