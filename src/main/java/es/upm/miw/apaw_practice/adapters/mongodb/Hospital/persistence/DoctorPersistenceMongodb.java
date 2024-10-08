package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.DoctorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.DoctorEntities;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.Hospital.Article;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.DoctorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.stream.Stream;
@Repository("doctorPersistence")
public class DoctorPersistenceMongodb implements DoctorPersistence {}
