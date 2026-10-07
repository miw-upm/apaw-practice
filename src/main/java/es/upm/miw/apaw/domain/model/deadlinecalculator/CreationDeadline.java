package es.upm.miw.apaw.domain.model.deadlinecalculator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationDeadline {

    @NotBlank
    private String title;

    private String courtFileNumber;

    @NotNull
    private LocalDate notificationDate;

    @NotNull
    @Positive
    private Integer days;

    private DayCountType dayCountType;

    @NotBlank
    private String region;

    @NotBlank
    private String city;

    @NotNull
    private UUID userId;
}
