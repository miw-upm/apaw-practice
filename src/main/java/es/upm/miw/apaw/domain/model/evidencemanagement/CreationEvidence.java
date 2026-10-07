package es.upm.miw.apaw.domain.model.evidencemanagement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationEvidence {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private EvidenceType evidenceType;

    @NotNull
    @PastOrPresent
    private LocalDateTime collectionDate;

    private String source;

    private Boolean confidential;

    @NotNull
    @Builder.Default
    private List<@NotNull UUID> custodyRecordIds = new ArrayList<>();
}
