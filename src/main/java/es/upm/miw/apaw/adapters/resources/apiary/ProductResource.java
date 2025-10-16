package es.upm.miw.apaw.adapters.resources.apiary;

import es.upm.miw.apaw.domain.models.apiary.Product;
import es.upm.miw.apaw.domain.models.apiary.ProductPriceUpdating;
import es.upm.miw.apaw.domain.services.apiary.ProductService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;

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
    public Product updatePut(@PathVariable String barcode, @Valid @RequestBody Product product) {
        product.setBarcode(barcode);
        return this.productService.updatePut(product);
    }

    @PatchMapping
    public void updatePrices(@Valid @RequestBody List<@Valid ProductPriceUpdating> productPriceUpdatingList) {
        this.productService.updatePricesDTO(productPriceUpdatingList.stream());
    }
}

