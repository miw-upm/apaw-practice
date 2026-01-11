package es.upm.miw.apaw.adapters.resources.clothingstore;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.services.clothingstore.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(OrderResource.ORDERS)
public class OrderResource {

    public static final String ORDERS = "/clothingstore/orders";
    public static final String SEARCH = "/search";
    public static final String DISTINCT_IDS = "/distinct-ids";

    public record GarmentIdsDto(List<UUID> ids) {}

    private final OrderService orderService;

    @Autowired
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(SEARCH + DISTINCT_IDS)
    public GarmentIdsDto findDistinctGarmentIdsByInvoiceNumber(
            @RequestParam(value = "number", required = false) String number) {
        if (number == null || number.isBlank()) {
            throw new BadRequestException("Query param 'number' is required");
        }
        return new GarmentIdsDto(this.orderService.findDistinctGarmentIdsByInvoiceNumber(number));
    }
}
