package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos;

import java.util.Optional;

public interface CourtRepository {
    Optional<Integer> findByNumberId(Integer number);
}
