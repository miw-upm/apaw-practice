package es.upm.miw.apaw.domain.ports.out.credentials;

import es.upm.miw.apaw.domain.model.credentials.Credential;
import es.upm.miw.apaw.domain.model.credentials.CredentialFindCriteria;
import es.upm.miw.apaw.domain.model.credentials.CredentialVerificationReport;

import java.util.List;

public interface CredentialGateway {
    Credential create(Credential credential);

    List<Credential> find(CredentialFindCriteria criteria);

    List<CredentialVerificationReport> findVerificationReport();

    boolean existsByNumber(String number);

    boolean existsByRegistryCode(String registryCode);
}