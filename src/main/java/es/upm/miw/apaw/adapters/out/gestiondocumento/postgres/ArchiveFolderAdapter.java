package es.upm.miw.apaw.adapters.out.gestiondocumento.postgres;

import es.upm.miw.apaw.domain.ports.out.gestiondocumento.ArchiveFolderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArchiveFolderAdapter implements ArchiveFolderGateway {

    private final ArchiveFolderRepository archiveFolderRepository;
}