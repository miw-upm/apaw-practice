package es.upm.miw.apaw.domain.model.gestiondocumento;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ArchiveFolder {

    private UUID id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Integer caseYear;

    private LocalDateTime createdAt;

    private String department;

    @NotNull
    private String folderPath;

    private Boolean closed;

    @Valid
    @NotEmpty
    private List<@NotNull LegalDocument> documents;

    @Valid
    @NotNull
    private UserSnapshot owner;

    public ArchiveFolder(String name, Integer caseYear, String folderPath,
                         List<LegalDocument> documents, UserSnapshot owner) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.caseYear = caseYear;
        this.folderPath = folderPath;
        this.createdAt = LocalDateTime.now();
        this.closed = false;
        this.documents = documents;
        this.owner = owner;
    }
}