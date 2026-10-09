package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyFindCriteria;
import es.upm.miw.apaw.domain.ports.out.powerofattorney.PowerOfAttorneyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PowerOfAttorneyAdapter implements PowerOfAttorneyGateway {

    private final PowerOfAttorneyRepository powerOfAttorneyRepository;
    private final PowerOfAttorneyPartyRepository powerOfAttorneyPartyRepository;

    @Override
    @Transactional
    public PowerOfAttorney create(PowerOfAttorney powerOfAttorney) {
        PowerOfAttorneyEntity entity = new PowerOfAttorneyEntity(
                powerOfAttorney,
                this.powerOfAttorneyPartyRepository.getReferenceById(powerOfAttorney.getPrincipal().getId()),
                this.powerOfAttorneyPartyRepository.getReferenceById(powerOfAttorney.getAttorney().getId()));
        this.powerOfAttorneyRepository.save(entity);
        return powerOfAttorney;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PowerOfAttorney> find(PowerOfAttorneyFindCriteria criteria) {
        Specification<PowerOfAttorneyEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria.hasStatus()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("status"), criteria.getStatus()));
        }
        if (criteria.hasFullMentalCapacity()) {
            if(criteria.getFullMentalCapacity()){
                specification = specification.and((root, query, builder) -> builder.and(
                        builder.equal(root.join("principal").get("fullMentalCapacity"), criteria.getFullMentalCapacity()),
                        builder.equal(root.join("attorney").get("fullMentalCapacity"), criteria.getFullMentalCapacity())));
            }else{
                specification = specification.and((root, query, builder) -> builder.or(
                        builder.equal(root.join("principal").get("fullMentalCapacity"), criteria.getFullMentalCapacity()),
                        builder.equal(root.join("attorney").get("fullMentalCapacity"), criteria.getFullMentalCapacity())));
            }

        }
        return this.powerOfAttorneyRepository.findAll(specification).stream()
                .map(this::toDomain)
                .toList();
    }

    private PowerOfAttorney toDomain(PowerOfAttorneyEntity entity) {
        PowerOfAttorney powerOfAttorney = new PowerOfAttorney();
        BeanUtils.copyProperties(entity, powerOfAttorney, "principal", "attorney");
        powerOfAttorney.setPrincipal(entity.getPrincipal().toDomain());
        powerOfAttorney.setAttorney(entity.getAttorney().toDomain());
        return powerOfAttorney;
    }

    @Override
    public boolean existsByProtocolNumber(String protocolNumber) {
        return this.powerOfAttorneyRepository.existsByProtocolNumber(protocolNumber);
    }

    @Override
    public boolean isReferenced(UUID partyId) {
        return this.powerOfAttorneyRepository.existsByPrincipal_Id(partyId)
                || this.powerOfAttorneyRepository.existsByAttorney_Id(partyId);
    }
}
