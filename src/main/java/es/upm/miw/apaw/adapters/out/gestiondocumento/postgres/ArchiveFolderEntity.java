package es.upm.miw.apaw.adapters.out.gestiondocumento.postgres;

import es.upm.miw.apaw.adapters.out.gestiondocumento.postgres.LegalDocumentEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "gestiondocumento_archive_folder")
@Getter
@Setter
@NoArgsConstructor
public class ArchiveFolderEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Integer caseYear;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private String department;

    @Column(nullable = false, unique = true)
    private String folderPath;

    @Column(nullable = false)
    private Boolean closed;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "archive_folder_id")
    private List<LegalDocumentEntity> documents;

    @Column(nullable = false)
    private UUID ownerId;
}