package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos.OperatorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.OperatorEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.theme_park.Operator;
import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.OperatorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("operatorPersistence")
public class OperatorPersistenceMongodb implements OperatorPersistence {

    private final OperatorRepository operatorRepository;

    @Autowired
    public OperatorPersistenceMongodb(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    @Override
    public Stream<Operator> readAll() {
        return this.operatorRepository.findAll().stream()
                .map(OperatorEntity::toOperator);
    }

    @Override
    public Operator readByIdEmployee(String idEmployee) {
        return this.operatorRepository.findByIdEmployee(idEmployee)
                .orElseThrow(() -> new NotFoundException(" Operator idEmployee: " + idEmployee))
                .toOperator();
    }

    @Override
    public void delete(String idEmployee) {
        this.operatorRepository.deleteByIdEmployee(idEmployee);
    }
}
