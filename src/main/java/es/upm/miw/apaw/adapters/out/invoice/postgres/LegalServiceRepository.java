package es.upm.miw.apaw.adapters.out.invoice.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LegalServiceRepository extends JpaRepository<LegalServiceEntity, UUID> {
}