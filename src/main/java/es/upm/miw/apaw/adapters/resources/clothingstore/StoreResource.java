package es.upm.miw.apaw.adapters.resources.clothingstore;

import es.upm.miw.apaw.domain.models.clothingstore.Store;
import es.upm.miw.apaw.domain.services.clothingstore.StoreService;
import jakarta.validation.Valid;
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

    @GetMapping("/{id}")
    public Store readById(@PathVariable UUID id) {
        return this.storeService.readById(id);
    }

    @PutMapping("/{id}")
    public Store update(@PathVariable UUID id, @Valid @RequestBody Store store) {
        return this.storeService.update(id, store);
    }

    @PatchMapping("/{id}")
    public Store patch(@PathVariable UUID id, @RequestBody Store partialStore) {
        return this.storeService.patch(id, partialStore);
    }

}
