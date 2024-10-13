package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;


import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;

import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.PatientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("PatientPersistence")
public class PatientPersistenceMongodb implements PatientPersistence {

}