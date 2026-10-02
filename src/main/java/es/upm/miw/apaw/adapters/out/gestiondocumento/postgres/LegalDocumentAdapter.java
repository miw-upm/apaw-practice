package es.upm.miw.apaw.adapters.out.gestiondocumento.postgres;

import es.upm.miw.apaw.domain.ports.out.gestiondocumento.LegalDocumentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LegalDocumentAdapter implements LegalDocumentGateway {

    private final LegalDocumentRepository legalDocumentRepository;
}