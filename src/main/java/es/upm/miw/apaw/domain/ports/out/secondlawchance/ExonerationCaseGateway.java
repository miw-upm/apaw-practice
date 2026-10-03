package es.upm.miw.apaw.domain.ports.out.secondlawchance;

import es.upm.miw.apaw.domain.model.secondlawchance.ExonerationCase;

public interface ExonerationCaseGateway {
    ExonerationCase create(ExonerationCase exonerationCase);

    boolean existsByCaseNumber(String caseNumber);
}
