package es.upm.miw.apaw.domain.models.clinic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Pet {

    @NotNull
    private Long microchipNumber;

    @NotBlank
    private String name;

    @NotNull
    private Species species;

    @NotNull
    private Gender gender;

    private List<Appointment> appointments;

}