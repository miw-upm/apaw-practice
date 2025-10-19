package es.upm.miw.apaw.domain.services.warehouse;

import es.upm.miw.apaw.domain.persistenceports.warehouse.OrderDetailPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderDetailService {

    private final OrderDetailPersistence orderDetailPersistence;

    @Autowired
    public OrderDetailService(OrderDetailPersistence orderDetailPersistence) {
        this.orderDetailPersistence = orderDetailPersistence;
    }

    public BigDecimal findBySumUnitCostDistinctByPosition(String position) {
        return this.orderDetailPersistence.findBySumUnitCostDistinctByPosition(position);
    }

}
