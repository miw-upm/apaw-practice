package es.upm.miw.apaw.domain.models.airport;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardingGate {
    @NotNull
    @NotBlank
    private UUID Id;
    @NotNull
    @NotBlank
    private String number;
    @NotNull
    @NotBlank
    private String terminal;
    private Boolean opened;

    public void update(BoardingGate boardingGate) {

    }
}
