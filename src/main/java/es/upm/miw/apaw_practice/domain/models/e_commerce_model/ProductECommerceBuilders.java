package es.upm.miw.apaw_practice.domain.models.e_commerce_model;
import java.math.BigDecimal;
public interface ProductECommerceBuilders {

    interface ProductName {
        NumberProduct productName(String productName);
    }

    interface NumberProduct {
        UnitPrice numberProduct(Integer numberProduct);
    }

    interface UnitPrice {
        Builder unitPrice(BigDecimal unitPrice);
    }

    interface Builder {
        ProductECommerce build();
    }
}
