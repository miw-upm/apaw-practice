package es.upm.miw.apaw_practice.adapters.rest.e_commerce;

import es.upm.miw.apaw_practice.domain.models.e_commerce_model.CustomerECommerce;
import es.upm.miw.apaw_practice.domain.services.e_commerce.CustomerECommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CustomerECommerceResource.CUSTOMERS)
public class CustomerECommerceResource {

    static final String CUSTOMERS = "/ecommerce/customers";
    static final String SEARCH = "/search";
    private final CustomerECommerceService customerECommerceService;

    @Autowired
    public CustomerECommerceResource(CustomerECommerceService customerECommerceService) {
        this.customerECommerceService = customerECommerceService;
    }

    @GetMapping(SEARCH)
    public CustomerECommerce findByUserName(@RequestParam String userName) {
        return this.customerECommerceService.findByUserName(userName);
    }
}