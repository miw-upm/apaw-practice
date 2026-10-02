package es.upm.miw.apaw.domain.ports.out.immigrationissues;

import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;

public interface LawBasisGateway {

    LawBasis create(LawBasis lawBasis);

    boolean existsByLawCode(String lawCode);
}