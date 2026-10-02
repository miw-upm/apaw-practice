package es.upm.miw.apaw.adapters.out.gestiondocumento.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LegalDocumentRepository
        extends JpaRepository<LegalDocumentEntity, UUID> {
}