package es.upm.miw.apaw_practice.domain.services.theme_park;

import es.upm.miw.apaw_practice.domain.models.theme_park.Operator;
import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.OperatorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OperatorService {

    private final OperatorPersistence operatorPersistence;

    @Autowired
    public OperatorService(OperatorPersistence operatorPersistence) {
        this.operatorPersistence = operatorPersistence;
    }

    public Operator read(String idEmployee) {
        return this.operatorPersistence.readByIdEmployee(idEmployee);
    }

    public void delete(String idEmployee) {
        this.operatorPersistence.delete(idEmployee);
    }

}
