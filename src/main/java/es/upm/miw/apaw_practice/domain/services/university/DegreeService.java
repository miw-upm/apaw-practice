package es.upm.miw.apaw_practice.domain.services.university;

import es.upm.miw.apaw_practice.domain.models.university.Degree;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.DegreePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .filter(degree -> (minCapacity <= degree.getCapacity()) && (degree.getCapacity() <= maxCapacity));
    }
}
