package es.upm.miw.apaw.domain.services.legalprocedure;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalTaskStatusUpdate;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalTaskUsageReport;
import es.upm.miw.apaw.domain.ports.out.legalprocedure.LegalTaskGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LegalTaskService {
    private final LegalTaskGateway legalTaskGateway;

    public LegalTask create(LegalTask legalTask) {
        if (this.legalTaskGateway.existsByTitle(legalTask.getTitle())) {
            throw new ConflictException("Legal task title already exists: " + legalTask.getTitle());
        }
        legalTask.doDefault();
        return this.legalTaskGateway.create(legalTask);
    }

    public List<LegalTask> findAll() {
        return this.legalTaskGateway.findAll();
    }

    public List<LegalTaskUsageReport> findUsageReport() {
        return this.legalTaskGateway.findUsageReport();
    }

    public LegalTask read(UUID id) {
        return this.legalTaskGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Legal task id not found: " + id));
    }

    public LegalTask update(UUID id, LegalTask legalTask) {
        LegalTask storedLegalTask = this.read(id);
        if (!storedLegalTask.getTitle().equals(legalTask.getTitle())
                && this.legalTaskGateway.existsByTitle(legalTask.getTitle())) {
            throw new ConflictException("Legal task title already exists: " + legalTask.getTitle());
        }
        storedLegalTask.setTitle(legalTask.getTitle());
        storedLegalTask.setNotes(legalTask.getNotes());
        storedLegalTask.setTaskStatus(legalTask.getTaskStatus());
        return this.legalTaskGateway.update(storedLegalTask);
    }

    @Transactional
    public void updateTaskStatuses(List<LegalTaskStatusUpdate> updates) {
        this.assertUniqueIds(updates);
        List<LegalTask> legalTasks = updates.stream()
                .map(update -> {
                    LegalTask legalTask = this.read(update.id());
                    legalTask.setTaskStatus(update.taskStatus());
                    return legalTask;
                })
                .toList();
        legalTasks.forEach(this.legalTaskGateway::update);
    }

    private void assertUniqueIds(List<LegalTaskStatusUpdate> updates) {
        Set<UUID> ids = new HashSet<>();
        for (LegalTaskStatusUpdate update : updates) {
            if (!ids.add(update.id())) {
                throw new BadRequestException("Repeated legal task id: " + update.id());
            }
        }
    }

    public void delete(UUID id) {
        if (this.legalTaskGateway.isReferenced(id)) {
            throw new ConflictException("Legal task is referenced by a legal procedure: " + id);
        }
        this.legalTaskGateway.delete(id);
    }
}
