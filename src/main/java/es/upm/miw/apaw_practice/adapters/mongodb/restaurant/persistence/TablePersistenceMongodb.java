package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos.ClientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos.TableRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.ClientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.ReserveEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.TableEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.restaurant.Reserve;
import es.upm.miw.apaw_practice.domain.models.restaurant.Table;
import es.upm.miw.apaw_practice.domain.persistence_ports.restaurant.TablePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("tablePersistence")
public class TablePersistenceMongodb implements TablePersistence {

    private final TableRepository tableRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public TablePersistenceMongodb(TableRepository tableRepository, ClientRepository clientRepository){
        this.tableRepository = tableRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public boolean existNumber(Integer number) {
        return this.tableRepository.findByNumber(number)
                .isPresent();
    }

    @Override
    public Table updateNumPeople(Table table) {
        TableEntity tableEntity = this.readByNumber(table.getNumber());
        List<ReserveEntity> reserveEntities = table.getReserves().stream()
                .map(reserve -> new ReserveEntity(
                        reserve.getReservationDate(),
                        reserve.getNumPeople(),
                        reserve.getHolder())
                ).collect(Collectors.toList());
        tableEntity.setReserves(reserveEntities);
        return this.tableRepository.save(tableEntity).toTable();
    }

    @Override
    public TableEntity readByNumber(Integer id) {
        return this.tableRepository.findByNumber(id).orElseThrow(()->new NotFoundException("Table number: "+id));
    }

    @Override
    public Stream<Table> readAll() {
        return this.tableRepository.findAll().stream()
                .map(TableEntity::toTable);
    }

    @Override
    public Table update(Table table) {
        TableEntity tableEntity = this.readByNumber(table.getNumber());
        tableEntity.fromTable(table);
        return this.tableRepository.save(tableEntity).toTable();
    }

    private Integer getTotalNumPeople(String category){
        return this.clientRepository.findAll().stream()
                .map(ClientEntity::toClient)
                .filter(client -> client.getWaiters().stream()
                        .anyMatch(waiter -> category.equals(waiter.getCategory())))
                .flatMap(client -> client.getTable().getReserves().stream())
                .mapToInt(Reserve::getNumPeople)
                .sum();
    }

    @Override
    public Table findByCategoryWaiter(String category) {
        Table table = new Table();
        Reserve[] reserves = {new Reserve()};
        table.setReserves(Arrays.asList(reserves));
        table.getReserves().get(0).setNumPeople(this.getTotalNumPeople(category));
        return table;
    }

    @Override
    public Stream<Reserve> findHolderByNumber(Table table) {
        return table.getReserves().stream()
               .map(Reserve::ofHolder);
    }
}
