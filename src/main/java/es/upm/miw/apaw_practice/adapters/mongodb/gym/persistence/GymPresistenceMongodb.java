package es.upm.miw.apaw_practice.adapters.mongodb.gym.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.gym.daos.GymRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.daos.LessonRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.GymEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import es.upm.miw.apaw_practice.domain.models.gym.Gym;
import es.upm.miw.apaw_practice.domain.persistence_ports.gym.GymPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository("GymPersistence")
public class GymPresistenceMongodb implements GymPersistence {
    private final GymRepository gymRepository;
    private final LessonRepository lessonRepository;

    @Autowired
    public GymPresistenceMongodb(GymRepository gymRepository, LessonRepository lessonRepository) {
        this.gymRepository = gymRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Gym readByAddress(String address) {
        return this.gymRepository
                .findByAddress(address)
                .orElseThrow(() -> new NotFoundException("Gym Address: " + address)).ToGym();

    }

    @Override
    public Gym update(String address, Gym gym) {
        GymEntity addressUpdate = this.gymRepository.findByAddress(address)
                .orElseThrow(() -> new NotFoundException("gym Address: " + address));
        addressUpdate.setCellphone(gym.getCellphone());
        return this.gymRepository.save(addressUpdate).ToGym();

    }

    @Override
    public List<Athlete> findAthleteByGymLabel(String label) {
        Gym gym = this.gymRepository.findByLabel(label)
                .orElseThrow(() -> new NotFoundException("gym Label :" + label)).toGym();

        List<Athlete> athletes = gym.getCoach().stream().
                flatMap(coach -> coach.getLesson()
                        .getAthletes().stream()
                        .map(Athlete::nameof)

                ).distinct()
                .collect(Collectors.toList());


        Set<Athlete> athleteSet = new HashSet<>(athletes);
        athletes.clear();
        athletes.addAll(athleteSet);
        return athletes;

    }
}
