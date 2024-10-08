package es.upm.miw.apaw_practice.domain.services.university;

import es.upm.miw.apaw_practice.domain.models.university.Degree;
import es.upm.miw.apaw_practice.domain.models.university.DegreeUpdate;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.DegreePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class DegreeService {
    private final DegreePersistence degreePersistence;

    @Autowired
    public DegreeService(DegreePersistence degreePersistence) {
        this.degreePersistence = degreePersistence;
    }

    public Degree read(Integer code) {
        return degreePersistence.read(code);
    }

    public Stream<Degree> findByCapacityBetween(int minCapacity, int maxCapacity) {
        return degreePersistence
                .readAll()
                .filter(degree -> (minCapacity <= degree.getCapacity()))
                .filter(degree -> (degree.getCapacity() <= maxCapacity));
    }

    public void updateCapacities(List<DegreeUpdate> degreeUpdateList) {
        degreeUpdateList
                .forEach(degreeUpdate -> {
                    Degree updatingDegree = degreePersistence.read(degreeUpdate.getCode());
                    degreePersistence.update(updatingDegree.getCode(), degreeUpdate.applyTo(updatingDegree));
                });
    }
}
