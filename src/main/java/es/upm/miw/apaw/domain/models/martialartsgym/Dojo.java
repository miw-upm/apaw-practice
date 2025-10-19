package es.upm.miw.apaw.domain.models.martialartsgym;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Dojo {

    @EqualsAndHashCode.Include
    @NotNull
    @NotBlank
    private String cadastralReference;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    private LocalDate foundationDate;

    private List<Equipment> equipment;
}
