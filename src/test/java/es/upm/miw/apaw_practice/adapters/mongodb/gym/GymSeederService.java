package es.upm.miw.apaw_practice.adapters.mongodb.gym;

import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos.AthleteRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos.CoachRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos.GymRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
