package es.upm.miw.apaw.adapters.resources.clothingstore;

import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.services.clothingstore.GarmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.UUID;
import java.math.BigDecimal;
import java.util.stream.Stream;

@RestController
@RequestMapping(GarmentResource.GARMENTS)
public class GarmentResource {

    public static final String GARMENTS = "/clothingstore/garments";

    private final GarmentService garmentService;
    @Autowired
    public GarmentResource(GarmentService garmentService) {
        this.garmentService = garmentService;
    }

    @PostMapping
    public Garment create(@RequestBody Garment garment) {
        return this.garmentService.create(garment);
    }
    @GetMapping("/all")
    public Stream<Garment> readAll() {
        return this.garmentService.readAll();
    }

    @GetMapping
    public Stream<Garment> findByPriceBetween(@RequestParam BigDecimal min,
                                              @RequestParam BigDecimal max) {
        System.out.println("[DEBUG] /garments?min=" + min + "&max=" + max);

        return this.garmentService.findByPriceBetween(min, max);

    }
    @PutMapping("/{id}")
    public Garment update(@PathVariable UUID id, @RequestBody Garment garment) {
        return this.garmentService.update(id, garment);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.garmentService.delete(id);
    }


    @GetMapping("/search/sum-price")
    public BigDecimal sumDistinctPriceByMobile(@RequestParam String mobile) {
        return this.garmentService.sumDistinctPriceByMobile(mobile);
    }
}
