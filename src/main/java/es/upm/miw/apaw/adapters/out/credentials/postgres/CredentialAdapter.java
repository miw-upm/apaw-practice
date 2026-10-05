package es.upm.miw.apaw.adapters.out.credentials.postgres;

import es.upm.miw.apaw.domain.model.credentials.Credential;
import es.upm.miw.apaw.domain.ports.out.credentials.CredentialGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public boolean existsByNumber(String number) {
        return this.credentialRepository.existsByNumber(number);
    }

    @Override
    public boolean existsByRegistryCode(String registryCode) {
        return this.credentialRepository.existsByRegistryCode(registryCode);
    }
}