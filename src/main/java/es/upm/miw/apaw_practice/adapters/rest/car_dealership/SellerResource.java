package es.upm.miw.apaw_practice.adapters.rest.car_dealership;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.car_dealership.Seller;
import es.upm.miw.apaw_practice.domain.services.car_dealership.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(SellerResource.SELLERS)
public class SellerResource {

    static final String SELLERS ="/car-dealership/sellers";

    static final String ID_ID = "/{id}";

    static final String NAME = "/name";
    static final String SEARCH = "/search";

    private final SellerService sellerService;

    @Autowired
    public SellerResource(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping
    public Seller create(@RequestBody Seller seller) {
        return this.sellerService.create(seller);
    }

    @PutMapping(ID_ID + NAME)
    public Seller updateSellerName(@PathVariable String id, @RequestBody String name) {
        return this.sellerService.updateSellerName(id, name);
    }

    @PatchMapping(ID_ID)
    public Seller updateSellerSurname(@PathVariable String id, @RequestBody String surname) {
        return this.sellerService.updateSellerSurname(id, surname);
    }

    @GetMapping(SEARCH)
    public Stream<String> findUniqueSurnamesMore20000ByBrand(@RequestParam String q) { //q=brand:brand
        String brand = new LexicalAnalyzer().extractWithAssure(q, "brand");
        return this.sellerService.findUniqueSurnamesMore20000ByBrand(brand);
    }
}
