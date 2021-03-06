package es.upm.miw.apaw_practice.adapters.mongodb.project.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.project.daos.UserStoryRepository;
import es.upm.miw.apaw_practice.domain.models.project.UserStory;
import es.upm.miw.apaw_practice.domain.persistence_ports.project.UserStoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("userStoryPersistence")
public class UserStoryPersistenceMongodb implements UserStoryPersistence {

    private UserStoryRepository userStoryRepository;

    @Autowired
    public UserStoryPersistenceMongodb(UserStoryRepository userStoryRepository) {
        this.userStoryRepository = userStoryRepository;
    }

    @Override
    public void delete(String id) {
        this.userStoryRepository.deleteById(id);
    }

    @Override
    public Stream<UserStory> readAll() {
        return this.userStoryRepository.findAll().stream()
                .map(userStoryEntity -> userStoryEntity.toUserStory());
    }
}
