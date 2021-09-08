package es.upm.miw.apaw_practice.domain.persistence_ports.shop;

import es.upm.miw.apaw_practice.domain.models.shop.Tag;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TagPersistence {

    Tag readById(String id);

    void deleteById(String id);

    Stream<Tag> readAll();
}
