package es.upm.miw.apaw.adapters.resources.apiary;

import es.upm.miw.apaw.domain.models.apiary.Product;
import es.upm.miw.apaw.domain.models.shop.ArticleItem;
import es.upm.miw.apaw.domain.models.shop.ShoppingCart;
import es.upm.miw.apaw.domain.services.apiary.ProductService;
import es.upm.miw.apaw.domain.services.apiary.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ProductResource.PRODUCTS)
public class ProductResource {
    public static final String PRODUCTS = "/apiary/products";
    public static final String BARCODE_ID = "/barcode";

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }


    @PutMapping(BARCODE_ID)
    public Product updatePrice(@PathVariable String barcode, @RequestBody BigDecimal newPrice) {
        return this.productService.updatePrice(barcode, newPrice);
    }
}
