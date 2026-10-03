package es.upm.miw.apaw.adapters.out.secondlawchance.postgres;

import es.upm.miw.apaw.domain.model.secondlawchance.Debt;
import es.upm.miw.apaw.domain.ports.out.secondlawchance.DebtGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DebtAdapter implements DebtGateway {
    private final DebtRepository debtRepository;
    private final ExonerationCaseRepository exonerationCaseRepository;

    @Override
    public Debt create(Debt debt) {
        return this.debtRepository
                .save(new DebtEntity(debt))
                .toDomain();
    }

    @Override
    public List<Debt> findAll() {
        return this.debtRepository.findAllByOrderByIssueDateAscCreditorNameAscIdAsc().stream()
                .map(DebtEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<Debt> read(UUID id) {
        return this.debtRepository.findById(id)
                .map(DebtEntity::toDomain);
    }

    @Override
    public Debt update(Debt debt) {
        return this.debtRepository
                .save(new DebtEntity(debt))
                .toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.debtRepository.deleteById(id);
    }

    @Override
    public boolean isReferenced(UUID id) {
        return this.exonerationCaseRepository.existsByDebtsId(id);
    }

    @Override
    public boolean existsByContractNumber(String contractNumber) {
        return this.debtRepository.existsByContractNumber(contractNumber);
    }
}
