package es.upm.miw.apaw_practice.domain.models.delivery_food;

import java.time.LocalDateTime;
import java.util.List;

public interface DeliveryOrderBuilders {

    interface  Id {
        DeliveryAddress id(String id);
    }

    interface  DeliveryAddress {
        CustomerName deliveryAddress(String deliveryAddress);
    }

    interface  CustomerName {
        OrderDate customerName(String customerName);
    }

    interface  OrderDate {
        Delivered orderDate(LocalDateTime orderDate);
    }

    interface  Delivered {
        Optionals delivered(Boolean delivered);
    }

    interface Optionals {
        Optionals deliveryOrderItems(List<DeliveryOrderItem> deliveryOrderItems);

        DeliveryOrder build();
    }
}
