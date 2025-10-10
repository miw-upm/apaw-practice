package es.upm.miw.apaw.domain.persistenceports.studentcouncil;

import es.upm.miw.apaw.domain.models.studentcouncil.Representative;

import java.util.List;
import java.util.stream.Stream;

public interface RepresentativePersistence {
    Stream<Representative> readAll();
}
