package es.upm.miw.apaw_practice.adapters.mongodb.gym.persistence;


import es.upm.miw.apaw_practice.adapters.mongodb.gym.daos.CoachRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.daos.GymRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.daos.LessonRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.CoachEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.GymEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import es.upm.miw.apaw_practice.domain.models.gym.Coach;
import es.upm.miw.apaw_practice.domain.models.gym.Gym;
import es.upm.miw.apaw_practice.domain.models.gym.Lesson;
import es.upm.miw.apaw_practice.domain.persistence_ports.gym.LessonPersistence;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Repository("LessonPersistence")
public class LessonPresistenceMongodb implements LessonPersistence {
    private final LessonRepository lessonRepository;
    private final CoachRepository coachRepository;
    private final GymRepository gymRepository;

    public LessonPresistenceMongodb(LessonRepository lessonRepository, CoachRepository coachRepository, GymRepository gymRepository) {
        this.lessonRepository = lessonRepository;
        this.coachRepository = coachRepository;
        this.gymRepository = gymRepository;
    }

    @Override
    public Lesson findByTitle(String title) {
        return this.lessonRepository.findByTitle(title).
                orElseThrow(() -> new NotFoundException("Lesson with name :" + title)).toLesson();
    }

    @Override
    public List<String> findGymByTitleAndName(String title, String name) {
        Lesson lesson = findByTitle(title);
        Athlete athlete = lesson.getAthletes().
                stream().filter(athlete1 -> athlete1.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Athlete with Name" + name));
        List<String> labelList = new ArrayList<>();

        if (checkIfAthlete(athlete, lesson)) {
            List<Coach> coaches = this.coachRepository.findAll().stream()
                    .filter(coachEntity -> coachEntity.getLesson().getTitle().equals(title))
                    .map(CoachEntity::toCoach)
                    .collect(Collectors.toList());
            List<Gym> gymEntityList = this.gymRepository.findAll().stream().map(GymEntity::toGym)
                    .collect(Collectors.toList());
            for (Gym gymEntity : gymEntityList) {
                if (checkIfCoach(coaches, gymEntity)) {
                    labelList.add(gymEntity.getLabel());
                }
            }
            Set<String> stringSet = new HashSet<>(labelList);
            labelList.clear();
            labelList.addAll(stringSet);
        }
        return labelList;

    }

    private boolean checkIfAthlete(Athlete athlete, Lesson lesson) {
        for (Athlete athlete1 : lesson.getAthletes()) {
            if (athlete1 == athlete)
                return true;
        }
        return false;
    }

    private boolean checkIfCoach(List<Coach> coaches, Gym gym) {
        for (Coach coach : coaches) {
            for (Coach gymCoach : gym.getCoach()) {
                if (coach.getDni().equals(gymCoach.getDni())) {
                    return true;
                }
            }
        }
        return false;
    }

}
