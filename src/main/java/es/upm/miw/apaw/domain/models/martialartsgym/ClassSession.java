package es.upm.miw.apaw.domain.models.martialartsgym;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClassSession {

    @EqualsAndHashCode.Include
    private Integer referenceCode;

    @NotNull
    private Integer sessionLength;

    @NotNull
    @NotBlank
    private String difficultyLevel;

    @NotNull
    private Dojo dojo;

    private List<UserDto> attendees;
}
