package es.upm.miw.apawpractice.domain.persistenceports.shop;

import es.upm.miw.apawpractice.domain.models.shop.Tag;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TagPersistence {

    Tag readByName(String name);

    void delete(String name);

    Stream<Tag> readAll();
}
