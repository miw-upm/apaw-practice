package es.upm.miw.apaw.domain.model.appointment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentLocation {

    private UUID id;

    @NotBlank
    private String name;

    private String address;

    @NotBlank
    private String city;

    private String postalCode;

    private String room;

    private Integer floor;

    private LocalDateTime creationDate;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.creationDate = LocalDateTime.now();
    }
}
