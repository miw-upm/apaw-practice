package es.upm.miw.apaw_practice.domain.services.Class;

import es.upm.miw.apaw_practice.domain.models.Class.Learner;
import es.upm.miw.apaw_practice.domain.models.football.Stadium;
import es.upm.miw.apaw_practice.domain.persistence_ports.Class.LearnerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class LearnerService {
    private final LearnerPersistence learnerPersistence;

    @Autowired
    LearnerService(LearnerPersistence learnerPersistence){
        this.learnerPersistence = learnerPersistence;
    }

    public Stream<Learner> readAll(){ return this.learnerPersistence.readAll();}

    public void updateName(String name, Learner learner) {
        Learner myLearner = this.learnerPersistence.readByName(name);
        myLearner.setName(learner.getName());
        this.learnerPersistence.update(myLearner);
    }


}
