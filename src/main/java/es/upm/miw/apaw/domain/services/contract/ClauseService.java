package es.upm.miw.apaw.domain.services.contract;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.contract.Clause;
import es.upm.miw.apaw.domain.model.contract.ClauseUpdate;
import es.upm.miw.apaw.domain.ports.out.contract.ClauseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void delete(UUID id) {
        this.read(id);

        if (this.clauseGateway.isReferenced(id)) {
            throw new ConflictException(
                    "Clause is referenced by a contract: " + id
            );
        }

        this.clauseGateway.delete(id);
    }

    public List<Clause> findAll() {return this.clauseGateway.findAll();}

    public Clause patch(UUID id, ClauseUpdate patch) {
        Clause storedClause = this.read(id);

        if (patch.type() != null) {
            storedClause.setType(patch.type());
        }

        if (patch.notes() != null) {
            storedClause.setNotes(patch.notes());
        }

        if (patch.effectiveUntil() != null) {
            storedClause.setEffectiveUntil(patch.effectiveUntil());
        }

        return this.clauseGateway.update(storedClause);
    }
}