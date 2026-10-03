package es.upm.miw.apaw.domain.services.immigrationissues;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.immigrationissues.CreationImmigrationIssue;
import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssue;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasisUsageReport;
import es.upm.miw.apaw.domain.ports.out.immigrationissues.ImmigrationIssueGateway;
import es.upm.miw.apaw.domain.ports.out.immigrationissues.LawBasisGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImmigrationIssueService {

    private final ImmigrationIssueGateway immigrationIssueGateway;
    private final LawBasisGateway lawBasisGateway;
    private final UserFinder userFinder;

    public ImmigrationIssue create(CreationImmigrationIssue creation) {
        if (this.immigrationIssueGateway.existsBySubject(creation.getSubject())) {
            throw new ConflictException("Immigration issue subject already exists: " + creation.getSubject());
        }
        ImmigrationIssue immigrationIssue = new ImmigrationIssue();
        BeanUtils.copyProperties(creation, immigrationIssue);
        immigrationIssue.setLawBases(creation.getLawBasisIds().stream()
                .map(this::readLawBasis)
                .toList());
        immigrationIssue.setUserSnapshot(this.userFinder.read(creation.getUserId()));
        immigrationIssue.doDefault();
        return this.immigrationIssueGateway.create(immigrationIssue);
    }

    private LawBasis readLawBasis(UUID id) {
        return this.lawBasisGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Law basis id not found: " + id));
    }

    public List<LawBasisUsageReport> findLawBasisUsageReport() {
        return this.immigrationIssueGateway.findLawBasisUsageReport();
    }
}