package es.upm.miw.apaw.adapters.resources.clothingstore;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.services.clothingstore.GarmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping(GarmentResource.GARMENTS)
public class GarmentResource {

    public static final String GARMENTS = "/clothingstore/garments";
    public static final String SEARCH = "/search";
    public static final String SUM_PRICE = "/sum-price";
    public static final String DISTINCT_IDS = "/distinct-ids";

    public record SumDto(BigDecimal sum) {}
    public record GarmentIdsDto(List<UUID> ids) {}

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

    @GetMapping(SEARCH + SUM_PRICE)
    public SumDto sumDistinctPriceByMobile(@RequestParam(value = "mobile", required = false) String mobile) {
        if (mobile == null || mobile.isBlank()) {
            throw new BadRequestException("Query param 'mobile' is required");
        }
        BigDecimal sum = this.garmentService.sumDistinctPriceByMobile(mobile);
        return new SumDto(sum);
    }

    @GetMapping(SEARCH + DISTINCT_IDS)
    public GarmentIdsDto findDistinctIdsByInvoiceNumber(
            @RequestParam(value = "number", required = false) String number) {
        if (number == null || number.isBlank()) {
            throw new BadRequestException("Query param 'number' is required");
        }
        return new GarmentIdsDto(this.garmentService.findDistinctIdsByInvoiceNumber(number));
    }
}
