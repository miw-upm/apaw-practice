package es.upm.miw.apaw.adapters.resources.apiary;

import es.upm.miw.apaw.adapters.resources.apiary.SaleResource;
import es.upm.miw.apaw.domain.models.apiary.Sale;
import es.upm.miw.apaw.domain.services.apiary.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(SaleResource.SALES)

public class SaleResource {
    public static final String SALES = "/apiary/sales";

    public static final String ID_SALE = "/{idSale}";

    private final SaleService saleService;

    @Autowired
    public SaleResource(SaleService saleService) {
        this.saleService = saleService;
    }

    @DeleteMapping(ID_SALE)
    public void delete(@PathVariable int idSale) {
        this.saleService.delete(idSale);
    }

    @PostMapping
    public Sale create(@Valid @RequestBody Sale sale) {
        return this.saleService.create(sale);
    }
}
