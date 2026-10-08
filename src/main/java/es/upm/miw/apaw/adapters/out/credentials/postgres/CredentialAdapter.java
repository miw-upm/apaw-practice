package es.upm.miw.apaw.adapters.out.credentials.postgres;

import es.upm.miw.apaw.domain.model.credentials.Credential;
import es.upm.miw.apaw.domain.model.credentials.CredentialFindCriteria;
import es.upm.miw.apaw.domain.model.credentials.CredentialVerificationReport;
import es.upm.miw.apaw.domain.ports.out.credentials.CredentialGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import es.upm.miw.apaw.domain.model.credentials.VerificationStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CredentialAdapter implements CredentialGateway {

    private final CredentialRepository credentialRepository;
    private final VerificationRepository verificationRepository;

    @Override
    @Transactional
    public Credential create(Credential credential) {
        CredentialEntity credentialEntity = new CredentialEntity(credential);

        List<VerificationEntity> verificationEntities = credential.getVerifications().stream()
                .map(verification -> this.verificationRepository.getReferenceById(verification.getId()))
                .collect(Collectors.toCollection(ArrayList::new));

        credentialEntity.setVerifications(verificationEntities);

        this.credentialRepository.save(credentialEntity);

        return credential;
    }

    @Override
    public List<Credential> find(CredentialFindCriteria criteria) {
        Specification<CredentialEntity> specification = this.buildSpecification(criteria);

        return this.credentialRepository.findAll(specification, Sort.by("number")).stream()
                .map(CredentialEntity::toSummary)
                .toList();
    }

    @Override
    public List<CredentialVerificationReport> findVerificationReport() {
        return this.credentialRepository.findCredentialVerificationReport();
    }

    private Specification<CredentialEntity> buildSpecification(CredentialFindCriteria criteria) {
        Specification<CredentialEntity> specification =
                (root, query, builder) -> builder.conjunction();

        if (criteria.hasCredentialType()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("credentialType"), criteria.getCredentialType()));
        }

        if (criteria.hasExpired()) {
            specification = specification.and((root, query, builder) ->
                    criteria.getExpired()
                            ? builder.and(
                            builder.isNotNull(root.get("expirationDate")),
                            builder.lessThan(
                                    root.get("expirationDate"),
                                    LocalDate.now()))
                            : builder.or(
                            builder.isNull(root.get("expirationDate")),
                            builder.greaterThanOrEqualTo(
                                    root.get("expirationDate"),
                                    LocalDate.now())));
        }

        specification = this.addVerificationStatus(
                specification, criteria.getVerificationStatus());

        return specification;
    }

    private Specification<CredentialEntity> addVerificationStatus(
            Specification<CredentialEntity> specification,
            VerificationStatus verificationStatus) {

        if (verificationStatus == null) {
            return specification;
        }

        return specification.and((root, query, builder) -> {
            query.distinct(true);
            return builder.equal(
                    root.join("verifications").get("verificationStatus"),
                    verificationStatus);
        });
    }

    @Override
    public boolean existsByNumber(String number) {
        return this.credentialRepository.existsByNumber(number);
    }

    @Override
    public boolean existsByRegistryCode(String registryCode) {
        return this.credentialRepository.existsByRegistryCode(registryCode);
    }
}