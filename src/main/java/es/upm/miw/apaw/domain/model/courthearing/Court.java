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
    
    private CourtType type;

    private List<CourtHearing> courtHearings;

    public void doDefault() {
        this.id = UUID.randomUUID();
    }

    public Court ofSummary() {
        return Court.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .city(this.city)
                .phone(this.phone)
                .openingTime(this.openingTime)
                .closingTime(this.closingTime)
                .type(this.type)
                .build();
    }

}