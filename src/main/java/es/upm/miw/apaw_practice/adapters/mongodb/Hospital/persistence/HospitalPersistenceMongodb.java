package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.HospitalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.HospitalEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hospital.Hospital;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.HospitalPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("hospitalPersistence")
public class HospitalPersistenceMongodb implements HospitalPersistence {



}
