package es.upm.miw.apaw.adapters.out.credentials.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CredentialRepository extends JpaRepository<CredentialEntity, UUID>,
        JpaSpecificationExecutor<CredentialEntity> {

    boolean existsByVerificationsId(UUID verificationId);

    boolean existsByNumber(String number);

    boolean existsByRegistryCode(String registryCode);
}