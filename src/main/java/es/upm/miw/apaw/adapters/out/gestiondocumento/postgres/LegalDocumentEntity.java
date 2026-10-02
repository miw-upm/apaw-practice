package es.upm.miw.apaw.adapters.out.gestiondocumento.postgres;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "gestiondocumento_legal_document")
@Getter
@Setter
@NoArgsConstructor
public class LegalDocumentEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String documentType;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false, updatable = false)
    private LocalDateTime registeredAt;

    private LocalDateTime archivedAt;

    @Column(nullable = false)
    private Integer version;
}