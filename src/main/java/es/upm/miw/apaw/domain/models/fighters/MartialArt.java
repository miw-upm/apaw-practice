package es.upm.miw.apaw.domain.models.fighters;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MartialArt {
    @NotBlank
    private String discipline;
    private String origin;
    private String description;
    @NotNull
    private Boolean striking;
    @NotNull
    private Boolean grappling;
}
