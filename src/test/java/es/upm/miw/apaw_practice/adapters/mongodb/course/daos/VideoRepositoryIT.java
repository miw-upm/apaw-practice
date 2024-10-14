package es.upm.miw.apaw_practice.adapters.mongodb.course.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.VideoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class VideoRepositoryIT {
    @Autowired
    private VideoRepository videoRepository;

    @Test
    void testFindByName(){
        assertTrue(this.videoRepository.findByName("Introducción a Python").isPresent());
        VideoEntity videoEntity = this.videoRepository.findByName("Introducción a Python").get();
        assertEquals(LocalTime.of(0, 15).toString() , videoEntity.getDuration().toString());
        assertEquals(LocalDateTime.of(2023, 1, 10, 10, 0).toString(), videoEntity.getCreationDate().toString());
    }

}
