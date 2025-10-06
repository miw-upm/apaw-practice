package es.upm.miw.apaw.adapters.mongodb.sports.academy.persistence;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.daos.LegalGuardianRepository;
import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.LegalGuardianEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.sports.academy.LegalGuardian;
import es.upm.miw.apaw.domain.persistenceports.sports.academy.ILegalGuardianPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository("legalGuardianPersistence")
public class LegalGuardianPersistenceMongodb implements ILegalGuardianPersistence {

    private final LegalGuardianRepository legalGuardianRepository;

    @Autowired
    public LegalGuardianPersistenceMongodb(LegalGuardianRepository legalGuardianRepository) {
        this.legalGuardianRepository = legalGuardianRepository;
    }

    @Override
    public Stream<LegalGuardian> getAll(int page, int pageSize) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return this.legalGuardianRepository
                .findAll(pageable)
                .stream()
                .map(LegalGuardianEntity::toLegalGuardian);
    }

    @Override
    public LegalGuardian create(LegalGuardian legalGuardian) {
        return this.legalGuardianRepository
                .save(new LegalGuardianEntity(legalGuardian))
                .toLegalGuardian();
    }

    @Override
    public LegalGuardian update(UUID id, LegalGuardian legalGuardian) {
        LegalGuardianEntity legalGuardianEntity = this.legalGuardianRepository
                .findByUserDtoId(legalGuardian.getUser().getId())
                .orElseThrow(() -> new NotFoundException("Legal Guardian user dto id: " + id));
        legalGuardianEntity.fromLegalGuardian(legalGuardian);
        return this.legalGuardianRepository
                .save(legalGuardianEntity)
                .toLegalGuardian();
    }

    @Override
    public LegalGuardian getById(UUID id) {
        return this.legalGuardianRepository
                .findByUserDtoId(id)
                .orElseThrow(() -> new NotFoundException("Legal Guardian user dto id: " + id))
                .toLegalGuardian();
    }
}
