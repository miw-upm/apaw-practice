package es.upm.miw.apaw_practice.adapters.mongodb.coffee_shop.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.coffee_shop.daos.CoffeeClientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.coffee_shop.daos.DiningRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.coffee_shop.entities.DiningEntity;
import es.upm.miw.apaw_practice.domain.models.coffee_shop.Dining;
import es.upm.miw.apaw_practice.domain.persistence_ports.coffee_shop.DiningPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("diningPersistence")
public class DiningPersistenceMongodb implements DiningPersistence {
    private final DiningRepository diningRepository;
    private final CoffeeClientRepository coffeeClientRepository;

    @Autowired
    public DiningPersistenceMongodb(DiningRepository diningRepository, CoffeeClientRepository coffeeClientRepository) {
        this.diningRepository = diningRepository;
        this.coffeeClientRepository = coffeeClientRepository;
    }

    @Override
    public boolean existDiningNumber(String dining) {
        return this.diningRepository
                .findByDiningNumber(dining)
                .isPresent();
    }
    @Override
    public Dining create(Dining dining) {
        return this.diningRepository
                .save(new DiningEntity(dining))
                .toDining();
    }

    public List<String> getLocationsByClientName(String name) {
        return this.coffeeClientRepository.findAll()
                .stream()
                .filter(clientEntity -> clientEntity.getName().equals(name))
                .map(client -> client.getDiningEntity().getLocation())
                .distinct()
                .toList();
    }



}
