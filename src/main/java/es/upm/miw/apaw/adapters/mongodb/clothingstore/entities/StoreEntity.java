package es.upm.miw.apaw.adapters.mongodb.clothingstore.entities;

import es.upm.miw.apaw.domain.models.clothingstore.Order;
import es.upm.miw.apaw.domain.models.clothingstore.Store;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "stores")
public class StoreEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private String name;
    private String address;

    private List<OrderEntity> orders;

    public StoreEntity(Store store) {
        BeanUtils.copyProperties(store, this, "orders");
        if (this.id == null) this.id = UUID.randomUUID();
        this.orders = (store.getOrders() == null) ? null :
                store.getOrders().stream().map(OrderEntity::new).toList();
    }

    public Store toStore() {
        Store store = new Store();
        BeanUtils.copyProperties(this, store, "orders");
        if (this.orders != null)
            store.setOrders(this.orders.stream().map(OrderEntity::toOrder).toList());
        return store;
    }

    public void fromStore(Store store) {
        BeanUtils.copyProperties(store, this, "orders");
        this.orders = (store.getOrders() == null) ? null :
                store.getOrders().stream().map(OrderEntity::new).toList();
    }
}

