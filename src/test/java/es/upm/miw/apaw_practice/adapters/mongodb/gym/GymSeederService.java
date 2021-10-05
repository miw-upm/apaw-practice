package es.upm.miw.apaw_practice.adapters.mongodb.gym;

import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.AthleteEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.CoachEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.GymEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.LessonEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos.AthleteRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos.CoachRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos.GymRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos.LessonRepository;

import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class GymSeederService {
    @Autowired
    private GymRepository gymRepository ;
    @Autowired
    private AthleteRepository athleteRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CoachRepository coachRepository;

    public void seedDatabase(){


    }
    public void deleteAll(){
        this.athleteRepository.deleteAll();
        this.coachRepository.deleteAll();
        this.gymRepository.deleteAll();
        this.lessonRepository.deleteAll();
    }

}
