package es.upm.miw.apaw.adapters.out.immigrationissues.postgres;

import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssue;
import es.upm.miw.apaw.domain.ports.out.immigrationissues.ImmigrationIssueGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ImmigrationIssueAdapter implements ImmigrationIssueGateway {

    private final ImmigrationIssueRepository immigrationIssueRepository;
    private final LawBasisRepository lawBasisRepository;

    @Override
    @Transactional
    public ImmigrationIssue create(ImmigrationIssue immigrationIssue) {
        ImmigrationIssueEntity immigrationIssueEntity = new ImmigrationIssueEntity(immigrationIssue);
        immigrationIssueEntity.setLawBases(immigrationIssue.getLawBases().stream()
                .map(lawBasis -> this.lawBasisRepository.getReferenceById(lawBasis.getId()))
                .collect(Collectors.toCollection(ArrayList::new)));
        this.immigrationIssueRepository.save(immigrationIssueEntity);
        return immigrationIssue;
    }

    @Override
    public boolean existsBySubject(String subject) {
        return this.immigrationIssueRepository.existsBySubject(subject);
    }
}