package es.upm.miw.apaw.domain.services.credentials;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.credentials.Credential;
import es.upm.miw.apaw.domain.model.credentials.CreationCredential;
import es.upm.miw.apaw.domain.model.credentials.CredentialFindCriteria;
import es.upm.miw.apaw.domain.model.credentials.Verification;
import es.upm.miw.apaw.domain.ports.out.credentials.CredentialGateway;
import es.upm.miw.apaw.domain.ports.out.credentials.VerificationGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final CredentialGateway credentialGateway;
    private final VerificationGateway verificationGateway;
    private final UserFinder userFinder;

    public Credential create(CreationCredential creation) {
        if (this.credentialGateway.existsByNumber(creation.getNumber())) {
            throw new ConflictException(
                    "Credential number already exists: " + creation.getNumber());
        }

        if (creation.getRegistryCode() != null
                && this.credentialGateway.existsByRegistryCode(creation.getRegistryCode())) {
            throw new ConflictException(
                    "Credential registry code already exists: " + creation.getRegistryCode());
        }

        List<Verification> verifications = creation.getVerificationIds().stream()
                .map(this::readVerification)
                .toList();

        UserSnapshot user = this.userFinder.read(creation.getUserId());

        Credential credential = new Credential();
        BeanUtils.copyProperties(creation, credential);
        credential.setVerifications(verifications);
        credential.setUser(user);
        credential.doDefault();

        return this.credentialGateway.create(credential);
    }

    public List<Credential> find(CredentialFindCriteria criteria) {
        List<Credential> credentials = this.credentialGateway.find(criteria);

        if (credentials.isEmpty()) {
            return List.of();
        }

        Set<UUID> userIds = credentials.stream()
                .map(credential -> credential.getUser().getId())
                .collect(Collectors.toSet());

        List<UserSnapshot> users = this.userFinder.findByIds(userIds);

        Map<UUID, UserSnapshot> usersById = users.stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));

        return credentials.stream()
                .map(credential -> this.enrichUser(credential, usersById))
                .filter(credential -> this.matchesUserEmail(criteria, credential))
                .map(Credential::ofSummary)
                .toList();
    }

    private Credential enrichUser(
            Credential credential,
            Map<UUID, UserSnapshot> usersById) {

        UUID userId = credential.getUser().getId();
        UserSnapshot user = usersById.get(userId);

        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }

        credential.setUser(user);
        return credential;
    }

    private boolean matchesUserEmail(
            CredentialFindCriteria criteria,
            Credential credential) {

        return !criteria.hasUserEmail()
                || criteria.getUserEmail().equals(credential.getUser().getEmail());
    }

    private Verification readVerification(UUID id) {
        return this.verificationGateway.read(id)
                .orElseThrow(() ->
                        new NotFoundException("Verification id not found: " + id));
    }
}