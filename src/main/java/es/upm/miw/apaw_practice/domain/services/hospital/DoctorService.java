package es.upm.miw.apaw_practice.domain.services.hospital;

import es.upm.miw.apaw_practice.domain.persistence_ports.hospital.DoctorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class DoctorService {

    private final DoctorPersistence doctorPersistence;

    @Autowired
    public DoctorService(DoctorPersistence doctorPersistence){
        this.doctorPersistence = doctorPersistence;
    }

    public Stream<String> readDoctorNicks() {
        return this.doctorPersistence.readNicks();
    }
}
