package es.upm.miw.apaw.domain.services.secondlawchance;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.secondlawchance.CreationExonerationCase;
import es.upm.miw.apaw.domain.model.secondlawchance.Debt;
import es.upm.miw.apaw.domain.model.secondlawchance.ExonerationCase;
import es.upm.miw.apaw.domain.ports.out.secondlawchance.DebtGateway;
import es.upm.miw.apaw.domain.ports.out.secondlawchance.ExonerationCaseGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExonerationCaseService {
    private final ExonerationCaseGateway exonerationCaseGateway;
    private final DebtGateway debtGateway;
    private final UserFinder userFinder;

    public ExonerationCase create(CreationExonerationCase creation) {
        if (this.exonerationCaseGateway.existsByCaseNumber(creation.getCaseNumber())) {
            throw new ConflictException("Exoneration case number already exists: " + creation.getCaseNumber());
        }
        this.assertUniqueDebtIds(creation.getDebtIds());
        ExonerationCase exonerationCase = new ExonerationCase();
        BeanUtils.copyProperties(creation, exonerationCase);
        exonerationCase.setDebts(creation.getDebtIds().stream()
                .map(this::readDebt)
                .toList());
        exonerationCase.setUserSnapshot(this.userFinder.read(creation.getUserId()));
        exonerationCase.doDefault();
        return this.exonerationCaseGateway.create(exonerationCase);
    }

    private void assertUniqueDebtIds(List<UUID> debtIds) {
        Set<UUID> ids = new HashSet<>();
        for (UUID id : debtIds) {
            if (!ids.add(id)) {
                throw new BadRequestException("Repeated debt id: " + id);
            }
        }
    }

    private Debt readDebt(UUID id) {
        return this.debtGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Debt id not found: " + id));
    }
}
