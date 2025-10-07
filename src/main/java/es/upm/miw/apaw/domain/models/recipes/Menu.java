package es.upm.miw.apaw.domain.models.recipes;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @NotNull
    @NotBlank
    private Long internalCode;
    @NotNull
    @NotBlank
    private String caption;
    @NotNull
    @NotBlank
    private LocalDate startDate;
    private String type;
    private UserDto user;
    private List<Recipe> recipes;
}
