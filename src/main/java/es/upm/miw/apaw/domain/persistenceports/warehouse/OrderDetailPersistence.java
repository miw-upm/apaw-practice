package es.upm.miw.apaw.domain.persistenceports.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface OrderDetailPersistence {

    Stream<OrderDetail> readAll();
    OrderDetail read(UUID id);

}
