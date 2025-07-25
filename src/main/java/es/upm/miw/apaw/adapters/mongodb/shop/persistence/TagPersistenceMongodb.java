package es.upm.miw.apaw.adapters.mongodb.shop.persistence;

import es.upm.miw.apaw.adapters.mongodb.shop.daos.TagRepository;
import es.upm.miw.apaw.adapters.mongodb.shop.entities.TagEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.shop.Tag;
import es.upm.miw.apaw.domain.persistenceports.shop.TagPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("tagPersistence")
public class TagPersistenceMongodb implements TagPersistence {

    private final TagRepository tagRepository;

    @Autowired
    public TagPersistenceMongodb(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Stream<Tag> readAll() {
        return this.tagRepository.findAll().stream()
                .map(TagEntity::toTag);
    }

    @Override
    public Tag readByName(String name) {
        return this.tagRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(" Tag name: " + name))
                .toTag();
    }

    @Override
    public void delete(String name) {
        this.tagRepository.deleteByName(name);
    }
}
