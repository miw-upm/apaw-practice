package es.upm.miw.apawpractice.adapters.mongodb.shop.persistence;

import es.upm.miw.apawpractice.adapters.mongodb.shop.daos.TagRepository;
import es.upm.miw.apawpractice.adapters.mongodb.shop.entities.TagEntity;
import es.upm.miw.apawpractice.domain.exceptions.NotFoundException;
import es.upm.miw.apawpractice.domain.models.shop.Tag;
import es.upm.miw.apawpractice.domain.persistence_ports.shop.TagPersistence;
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
