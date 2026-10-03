package es.upm.miw.apaw.adapters.out.secondlawchance.postgres;

import es.upm.miw.apaw.domain.model.secondlawchance.ExonerationCase;
import es.upm.miw.apaw.domain.ports.out.secondlawchance.ExonerationCaseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ExonerationCaseAdapter implements ExonerationCaseGateway {
    private final ExonerationCaseRepository exonerationCaseRepository;
    private final DebtRepository debtRepository;

    @Override
    @Transactional
    public ExonerationCase create(ExonerationCase exonerationCase) {
        ExonerationCaseEntity exonerationCaseEntity = new ExonerationCaseEntity(exonerationCase);
        exonerationCaseEntity.setDebts(exonerationCase.getDebts().stream()
                .map(debt -> this.debtRepository.getReferenceById(debt.getId()))
                .collect(Collectors.toCollection(ArrayList::new)));
        this.exonerationCaseRepository.save(exonerationCaseEntity);
        return exonerationCase;
    }

    @Override
    public boolean existsByCaseNumber(String caseNumber) {
        return this.exonerationCaseRepository.existsByCaseNumber(caseNumber);
    }
}
