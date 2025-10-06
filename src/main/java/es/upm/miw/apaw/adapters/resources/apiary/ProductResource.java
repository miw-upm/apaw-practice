package es.upm.miw.apaw.adapters.resources.apiary;

import es.upm.miw.apaw.domain.models.apiary.Product;
import es.upm.miw.apaw.domain.services.apiary.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping(ProductResource.PRODUCTS)

public class ProductResource {

    public static final String PRODUCTS = "/apiary/products";
    public static final String BARCODE_ID = "/{barcode}";

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping(BARCODE_ID)
    public Product update(@PathVariable String barcode, @RequestBody Product product) {
        product.setBarcode(barcode);
        return this.productService.update(product);
    }

    @PatchMapping(BARCODE_ID)
    public Product updatePrice(@PathVariable String barcode, @RequestBody Map<String, BigDecimal> body) {
        BigDecimal newPrice = body.get("price");
        return this.productService.updatePrice(barcode, newPrice);
    }
}

