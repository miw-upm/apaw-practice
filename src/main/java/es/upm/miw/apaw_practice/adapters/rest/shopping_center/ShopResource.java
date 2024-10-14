package es.upm.miw.apaw_practice.adapters.rest.shopping_center;

import es.upm.miw.apaw_practice.domain.models.shopping_center.Employee;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Shop;
import es.upm.miw.apaw_practice.domain.services.shopping_center.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(ShopResource.SHOPS)
public class ShopResource {
    static final String SHOPS = "/shopping_center/shops";

    private final ShopService shopService;

    static final String ID_ID = "/{id}";
    static final String EMPLOYEES = "/employees";

    @Autowired
    public ShopResource(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public Stream<Shop> findAllShops() {
        return this.shopService.findAllShops();
    }

    @PutMapping(ID_ID + EMPLOYEES)
    public Shop updateEmployees(@PathVariable String id, @RequestBody List<Employee> employeeList) {
        return this.shopService.updateEmployees(id, employeeList);
    }
}
