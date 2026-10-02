package es.upm.miw.apaw.adapters.out.immigrationissues.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LawBasisRepository extends JpaRepository<LawBasisEntity, UUID> {

}