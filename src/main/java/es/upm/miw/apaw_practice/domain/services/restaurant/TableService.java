package es.upm.miw.apaw_practice.domain.services.restaurant;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.restaurant.Reserve;
import es.upm.miw.apaw_practice.domain.models.restaurant.Table;
import es.upm.miw.apaw_practice.domain.persistence_ports.restaurant.TablePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class TableService {

    private final TablePersistence tablePersistence;

    @Autowired
    public TableService(TablePersistence tablePersistence) {
        this.tablePersistence = tablePersistence;
    }

    public Stream<Reserve> findHolderByNumber(Integer number) {
        this.assertNumberExist(number);
        Table table = this.tablePersistence.readByNumber(number).toTable();
        return this.tablePersistence.findHolderByNumber(table);
    }

    private void assertNumberExist(Integer number) {
        if (!this.tablePersistence.existNumber(number)) {
            throw new ConflictException("Number no exist: " + number);
        }
    }

    public Table updateNumPeople(Integer id, List<Reserve> reserves) {
        this.assertNumberExist(id);
        Table table = this.tablePersistence.readByNumber(id).toTable();
        table.setReserves(reserves);
        return this.tablePersistence.updateNumPeople(table);
    }

    public void updateStyles(String style) {
        this.tablePersistence.readAll()
                .map(table -> {
                    Table newTable = new Table(
                            table.getNumber(),
                            table.isOccupied(),
                            table.getStyle(),
                            table.getPrice(),
                            table.getReserves()
                    );
                    newTable.setStyle(style);
                    return newTable;})
                .forEach(this.tablePersistence::update);
    }

    public Table findByCategoryWaiter(String category) {
        return this.tablePersistence.findByCategoryWaiter(category);
    }
}
