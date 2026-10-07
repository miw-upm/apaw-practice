package es.upm.miw.apaw.domain.services.secondlawchance;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.secondlawchance.CreationExonerationCase;
import es.upm.miw.apaw.domain.model.secondlawchance.Debt;
import es.upm.miw.apaw.domain.model.secondlawchance.ExonerationCase;
import es.upm.miw.apaw.domain.model.secondlawchance.ExonerationCaseFindCriteria;
import es.upm.miw.apaw.domain.ports.out.secondlawchance.DebtGateway;
import es.upm.miw.apaw.domain.ports.out.secondlawchance.ExonerationCaseGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        this.assertResolutionDate(creation.getResolutionDate());
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

    public List<ExonerationCase> find(ExonerationCaseFindCriteria criteria) {
        List<ExonerationCase> exonerationCases = this.exonerationCaseGateway.find(criteria);
        if (exonerationCases.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = exonerationCases.stream()
                .map(exonerationCase -> exonerationCase.getUserSnapshot().getId())
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> usersById = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        return exonerationCases.stream()
                .map(exonerationCase -> this.enrichUserSnapshot(exonerationCase, usersById))
                .filter(exonerationCase -> this.matchesUserMobile(criteria, exonerationCase))
                .map(ExonerationCase::ofSummary)
                .toList();
    }

    private ExonerationCase enrichUserSnapshot(ExonerationCase exonerationCase, Map<UUID, UserSnapshot> usersById) {
        UUID userId = exonerationCase.getUserSnapshot().getId();
        UserSnapshot user = usersById.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        exonerationCase.setUserSnapshot(user);
        return exonerationCase;
    }

    private boolean matchesUserMobile(ExonerationCaseFindCriteria criteria, ExonerationCase exonerationCase) {
        return !criteria.hasUserMobile()
                || criteria.getUserMobile().equals(exonerationCase.getUserSnapshot().getMobile());
    }

    private void assertResolutionDate(LocalDate resolutionDate) {
        if (resolutionDate != null && resolutionDate.isBefore(LocalDate.now())) {
            throw new BadRequestException("Resolution date cannot be before the filing date: " + resolutionDate);
        }
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
