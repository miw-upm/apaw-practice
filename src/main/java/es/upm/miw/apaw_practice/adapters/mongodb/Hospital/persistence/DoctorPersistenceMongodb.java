package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.DoctorPersistence;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("doctorPersistence")
public class DoctorPersistenceMongodb implements DoctorPersistence {

}
