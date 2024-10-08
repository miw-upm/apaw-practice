package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.AppoinmentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.AppoinmentEntities;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.Hospital.Appoinment;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.ArticlePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Repository("articlePersistence")
public class AppoibnebtPersistenceMongodb implements AppoinmentRepository {}
