package es.upm.miw.apaw.adapters.resources.warehouse;

import es.upm.miw.apaw.domain.services.warehouse.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(OrderDetailResource.ORDER_DETAILS)
public class OrderDetailResource {

    public static final String ORDER_DETAILS = "/warehouse/order-details";
    public static final String SEARCHES = "/searches";
    public static final String SUM_UNITCOST_BY_POSITION = SEARCHES + "/sum-unitcost-by-position/{position}";

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailResource(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping(SUM_UNITCOST_BY_POSITION)
    public BigDecimal findBySumUnitCostDistinctByPosition(
            @PathVariable String position) {
        return this.orderDetailService.findBySumUnitCostDistinctByPosition(position.toUpperCase());
    }

}
