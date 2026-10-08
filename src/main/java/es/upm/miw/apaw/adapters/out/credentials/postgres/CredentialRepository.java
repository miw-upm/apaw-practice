package es.upm.miw.apaw.adapters.out.credentials.postgres;

import es.upm.miw.apaw.domain.model.credentials.CredentialVerificationReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CredentialRepository extends JpaRepository<CredentialEntity, UUID>,
        JpaSpecificationExecutor<CredentialEntity> {

    boolean existsByVerificationsId(UUID verificationId);

    boolean existsByNumber(String number);

    boolean existsByRegistryCode(String registryCode);

    @Query("""
        select new es.upm.miw.apaw.domain.model.credentials.CredentialVerificationReport(
            credential.number,
            count(verification),
            sum(case when verification.verificationStatus =
                es.upm.miw.apaw.domain.model.credentials.VerificationStatus.VERIFIED
                then 1 else 0 end)
        )
        from CredentialEntity credential
        join credential.verifications verification
        group by credential.number
        order by count(verification) desc
        """)
    List<CredentialVerificationReport> findCredentialVerificationReport();
}