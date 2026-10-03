package es.upm.miw.apaw.domain.model.gestiondocumento;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LegalDocument {

    private UUID id;

    @NotNull
    private String documentType;

    @NotNull
    private String title;

    @NotNull
    private String fileName;

    private LocalDateTime registeredAt;

    private LocalDateTime archivedAt;

    private Integer version;

    public LegalDocument(String documentType, String title, String fileName) {
        this.id = UUID.randomUUID();
        this.documentType = documentType;
        this.title = title;
        this.fileName = fileName;
        this.registeredAt = LocalDateTime.now();
        this.version = 1;
    }
}