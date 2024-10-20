package es.upm.miw.apaw_practice.adapters.rest.e_commerce;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ProductECommerce;
import es.upm.miw.apaw_practice.domain.services.e_commerce.ProductECommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(ProductECommerceResource.PRODUCTS)
public class ProductECommerceResource {
    static final String PRODUCTS = "/products";
    private final ProductECommerceService productService;
    @Autowired
    public ProductECommerceResource(ProductECommerceService productECommerceService) {
        this.productService = productECommerceService;
    }

    @PostMapping
    public ProductECommerce create(@RequestBody ProductECommerce productECommerce) {
        return this.productService.create(productECommerce);
    }
}
