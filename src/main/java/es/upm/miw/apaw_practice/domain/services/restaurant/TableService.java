package es.upm.miw.apaw_practice.domain.services.restaurant;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.restaurant.Reserve;
import es.upm.miw.apaw_practice.domain.persistence_ports.restaurant.TablePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class TableService {

    private TablePersistence tablePersistence;

    @Autowired
    public TableService(TablePersistence tablePersistence){
        this.tablePersistence = tablePersistence;
    }

    public Stream<Reserve> readHoldersByNumber(Integer number) {
        this.assertNumberNoExist(number);
        return this.tablePersistence.readHoldersByNumber(number);
    }

    public void assertNumberNoExist(Integer number){
        if(!this.tablePersistence.existNumber(number)){
            throw new ConflictException("Number no exist: "+number);
        }
    }
}
