package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;


import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.PatientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("patientPersistence")
public class PatientPersistenceMongodb implements PatientPersistence {

}
