package es.upm.miw.apaw.domain.ports.out.secondlawchance;

import es.upm.miw.apaw.domain.model.secondlawchance.ExonerationCase;
import es.upm.miw.apaw.domain.model.secondlawchance.ExonerationCaseFindCriteria;

import java.util.List;

public interface ExonerationCaseGateway {
    ExonerationCase create(ExonerationCase exonerationCase);

    boolean existsByCaseNumber(String caseNumber);

    List<ExonerationCase> find(ExonerationCaseFindCriteria criteria);
}
