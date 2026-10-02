package es.upm.miw.apaw.domain.ports.out.immigrationissues;

import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;

import java.util.Optional;
import java.util.UUID;

public interface LawBasisGateway {

    LawBasis create(LawBasis lawBasis);

    boolean existsByLawCode(String lawCode);

    Optional<LawBasis> read(UUID id);
}