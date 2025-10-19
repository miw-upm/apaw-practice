package es.upm.miw.apaw.adapters.resources.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import es.upm.miw.apaw.domain.services.warehouse.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ProductItemResource.PRODUCT_ITEMS)
public class ProductItemResource {

    public static final String PRODUCT_ITEMS = "/warehouse/product-items";
    public static final String BARCODE = "/{barcode}";

    private final ProductItemService productItemService;

    @Autowired
    public ProductItemResource(ProductItemService productItemService) {
        this.productItemService = productItemService;
    }

    @PutMapping(BARCODE)
    public ProductItem update(@PathVariable String barcode, @RequestBody ProductItem productItem) {
        String normalizedBarcode = barcode.toUpperCase();
        productItem.setBarcode(normalizedBarcode);
        return this.productItemService.update(normalizedBarcode, productItem);
    }

}