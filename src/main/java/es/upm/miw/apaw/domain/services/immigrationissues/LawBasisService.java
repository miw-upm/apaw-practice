package es.upm.miw.apaw.domain.services.immigrationissues;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasisUpdate;
import es.upm.miw.apaw.domain.ports.out.immigrationissues.LawBasisGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LawBasisService {

    private final LawBasisGateway lawBasisGateway;

    public LawBasis create(LawBasis lawBasis) {
        if (this.lawBasisGateway.existsByLawCode(lawBasis.getLawCode())) {
            throw new ConflictException("Law basis law code already exists: " + lawBasis.getLawCode());
        }
        lawBasis.doDefault();
        return this.lawBasisGateway.create(lawBasis);
    }

    public LawBasis read(UUID id) {
        return this.lawBasisGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Law basis id not found: " + id));
    }

    public LawBasis update(UUID id, LawBasis lawBasis) {
        LawBasis storedLawBasis = this.read(id);

        if (!storedLawBasis.getLawCode().equals(lawBasis.getLawCode())
                && this.lawBasisGateway.existsByLawCode(lawBasis.getLawCode())) {
            throw new ConflictException("Law basis law code already exists: " + lawBasis.getLawCode());
        }

        storedLawBasis.setLawCode(lawBasis.getLawCode());
        storedLawBasis.setLawName(lawBasis.getLawName());
        storedLawBasis.setArticleNumber(lawBasis.getArticleNumber());
        storedLawBasis.setPublishedOn(lawBasis.getPublishedOn());
        storedLawBasis.setActive(lawBasis.getActive());

        return this.lawBasisGateway.update(storedLawBasis);
    }

    public LawBasis patch(UUID id, LawBasisUpdate patch) {
        LawBasis storedLawBasis = this.read(id);

        if (patch.lawCode() != null) {
            if (!storedLawBasis.getLawCode().equals(patch.lawCode())
                    && this.lawBasisGateway.existsByLawCode(patch.lawCode())) {
                throw new ConflictException("Law basis law code already exists: " + patch.lawCode());
            }
            storedLawBasis.setLawCode(patch.lawCode());
        }

        if (patch.lawName() != null) {
            storedLawBasis.setLawName(patch.lawName());
        }

        if (patch.articleNumber() != null) {
            storedLawBasis.setArticleNumber(patch.articleNumber());
        }

        if (patch.publishedOn() != null) {
            storedLawBasis.setPublishedOn(patch.publishedOn());
        }

        if (patch.active() != null) {
            storedLawBasis.setActive(patch.active());
        }

        return this.lawBasisGateway.update(storedLawBasis);
    }

    public void delete(UUID id) {
        this.read(id);

        if (this.lawBasisGateway.isReferenced(id)) {
            throw new ConflictException("Law basis is referenced by an immigration issue: " + id);
        }

        this.lawBasisGateway.delete(id);
    }

    public List<LawBasis> findAll() {
        return this.lawBasisGateway.findAll();
    }
}