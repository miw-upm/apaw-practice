package es.upm.miw.apaw_practice.adapters.mongodb.gym.persistence;


import es.upm.miw.apaw_practice.adapters.mongodb.gym.daos.AthleteRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.AthleteEntity;
import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import es.upm.miw.apaw_practice.domain.models.gym.AthleteNameUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.gym.AthletePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("AthletePersistence")
public class AthletePresistenceMongodb implements AthletePersistence {
    private final AthleteRepository athleteRepository;

    @Autowired
    public AthletePresistenceMongodb(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    @Override
    public Athlete creat(Athlete athlete) {
        return this.athleteRepository
                .save(new AthleteEntity(athlete))
                .toAthlete();
    }

    @Override
    public boolean existNie(String nie) {
        return this.athleteRepository
                .findByNie(nie)
                .isPresent();
    }


    @Override
    public void updateNextFumigation(AthleteNameUpdating athleteNameUpdating) {
        List<AthleteEntity> nameUpdat = this.athleteRepository.findAll().stream()
                .filter(nameUpd -> nameUpd.getName().equals(athleteNameUpdating.getOldName()))
                .collect(Collectors.toList());
        nameUpdat.forEach(name -> name.setName(athleteNameUpdating.getNewName()));
        this.athleteRepository.saveAll(nameUpdat);
    }

}
