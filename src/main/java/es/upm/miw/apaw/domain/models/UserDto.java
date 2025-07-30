package es.upm.miw.apaw.domain.models;

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
public class UserDto {
    private UUID id;
    @NotNull
    @NotBlank
    private String mobile;
    @NotNull
    @NotBlank
    private String firstName;
}
