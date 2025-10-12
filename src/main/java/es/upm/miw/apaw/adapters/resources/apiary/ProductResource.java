package es.upm.miw.apaw.adapters.resources.apiary;

import es.upm.miw.apaw.domain.models.apiary.Product;
import es.upm.miw.apaw.domain.services.apiary.ProductService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Product update(@PathVariable String barcode, @Valid @RequestBody Product product) {
        product.setBarcode(barcode);
        return this.productService.update(product);
    }

    @PatchMapping(BARCODE_ID)
    public Product updatePrice(@PathVariable String barcode, @RequestBody Map<String, BigDecimal> body) {
        if (body.get("price") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be provided");
        }
        BigDecimal newPrice = body.get("price");
        return this.productService.updatePrice(barcode, newPrice);
    }


}

