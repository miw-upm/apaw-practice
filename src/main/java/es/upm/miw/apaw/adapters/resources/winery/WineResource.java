package es.upm.miw.apaw.adapters.resources.winery;

import es.upm.miw.apaw.domain.models.winery.Wine;
import es.upm.miw.apaw.domain.services.winery.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(WineResource.WINES)
public class WineResource {

    public static final String WINES = "/winery/wines";

    public static final String ID = "/{id}";

    private final WineService wineService;

    @Autowired
    public WineResource(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping(ID)
    public Wine read(@PathVariable UUID id) {
        return this.wineService.read(id);
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable UUID id) {
        this.wineService.delete(id);
    }

    @PatchMapping
    public void updatePrices(@RequestBody List<Wine> wineList) { this.wineService.updatePrices(wineList.stream()); }
}
