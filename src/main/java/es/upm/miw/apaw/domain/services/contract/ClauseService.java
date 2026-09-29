package es.upm.miw.apaw.domain.services.contract;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.contract.Clause;
import es.upm.miw.apaw.domain.ports.out.contract.ClauseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClauseService {

    private final ClauseGateway clauseGateway;

    public Clause create(Clause clause) {
        clause.doDefault();
        return this.clauseGateway.create(clause);
    }

    public Clause read(UUID id) {
        return this.clauseGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Clause id not found: " + id));
    }

    public Clause update(UUID id, Clause clause) {
        Clause storedClause = this.read(id);

        storedClause.setTitle(clause.getTitle());
        storedClause.setType(clause.getType());
        storedClause.setContent(clause.getContent());
        storedClause.setEffectiveFrom(clause.getEffectiveFrom());
        storedClause.setEffectiveUntil(clause.getEffectiveUntil());
        storedClause.setNotes(clause.getNotes());
        storedClause.setVersion(clause.getVersion());

        return this.clauseGateway.update(storedClause);
    }
}