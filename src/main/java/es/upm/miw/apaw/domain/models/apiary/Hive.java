package es.upm.miw.apaw.domain.models.apiary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hive {
    @NotNull
    @NotBlank
    private Integer code;
    private String type;
    @NotNull
    private Boolean queen;
    private LocalDate installationDate;
    private Product product;
}
