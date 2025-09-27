package es.upm.miw.apaw.domain.models.sports.academy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.lang.Nullable;

public class Place {
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private String street;
    @NotNull
    @Positive
    private int number;
    @NotNull
    @NotBlank
    private String postalCode;
    @Nullable
    private String additionalInfo;
}
