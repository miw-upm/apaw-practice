package es.upm.miw.apaw.adapters.resources.clothingstore;

import es.upm.miw.apaw.domain.services.clothingstore.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping(StoreResource.STORES)
public class StoreResource {

    public static final String STORES = "/clothingstore/stores";

    private final StoreService storeService;

    public StoreResource(StoreService storeService) {
        this.storeService = storeService;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.storeService.delete(id);
    }
}
