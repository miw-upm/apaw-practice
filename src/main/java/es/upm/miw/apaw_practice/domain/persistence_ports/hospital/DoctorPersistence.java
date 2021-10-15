package es.upm.miw.apaw_practice.domain.persistence_ports.hospital;

import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface DoctorPersistence {

    Stream<String> readNicks();
}
