package es.upm.miw.apaw.domain.models.recruiting;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendee {
    private String firstName;
    private String lastName;
    @NotNull
    @NotBlank
    private String emailAddress;

    // Optional Aggregation with one UserDto
    private UserDto user;
}