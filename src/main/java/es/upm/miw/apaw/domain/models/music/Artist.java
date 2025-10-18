package es.upm.miw.apaw.domain.models.music;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @NotNull
    @NotBlank
    private String name;
    @PastOrPresent
    private LocalDate activeSince;
    @Min(0)
    private Long monthlyListeners;
    @NotNull
    private UserDto user;
    @Builder.Default
    private List<Song> songs = new ArrayList<>();
}