package es.upm.miw.apaw.domain.models.apiary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Apiary {
    @NotNull
    @NotBlank
    private String cadastralRef;
    private String location;
    @NotNull
    private String rega;
    private List<Hive> hives;
}
