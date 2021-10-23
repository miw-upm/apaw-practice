package es.upm.miw.apaw_practice.domain.persistence_ports.game_wow;

import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface FeaturePersistence {
    Feature create(Feature feature);
    Feature read(String part);
    Stream<Feature> readAll();
    Feature update(Feature feature);
    List<Feature> readBySpellPower(Integer spellPower);
}
