package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food;

import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos.DeliveryOrderItemRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos.DeliveryOrderRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos.MenuCategoryRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos.MenuRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.DeliveryOrderEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.DeliveryOrderItemEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.MenuCategoryEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.MenuEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryFoodSeederService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuCategoryRepository menuCategoryRepository;
    @Autowired
    private DeliveryOrderRepository deliveryOrderRepository;
    @Autowired
    private DeliveryOrderItemRepository deliveryOrderItemRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Delivery Food Initial Load -----------");

        List<MenuCategoryEntity> menuCategories = List.of(
                new MenuCategoryEntity("Salads", "Fresh and healthy salad options", true),
                new MenuCategoryEntity("Seafood", "Delicious seafood dishes", true),
                new MenuCategoryEntity("Vegetarian", "Tasty vegetarian options", true),
                new MenuCategoryEntity("Grilled", "Grilled items with unique flavors", true),
                new MenuCategoryEntity("Soups", "Warm and comforting soup varieties", false)
        );
        menuCategoryRepository.saveAll(menuCategories);

        List<MenuEntity> menus = List.of(
                new MenuEntity("Italian Feast", "A delightful selection of Italian dishes.",
                        List.of(menuCategories.get(1), menuCategories.get(2)), 4.8),
                new MenuEntity("Seafood Platter", "An exquisite assortment of fresh seafood.",
                        List.of(menuCategories.get(0), menuCategories.get(1)), 4.7),
                new MenuEntity("Vegetarian Delight", "A variety of tasty vegetarian options.",
                        List.of(menuCategories.get(0)), 4.5),
                new MenuEntity("Grill Master", "Grilled specialties with a unique flavor.",
                        List.of(menuCategories.get(1), menuCategories.get(3)), 4.6),
                new MenuEntity("Dessert Extravaganza", "A sweet ending with various desserts.",
                        List.of(menuCategories.get(2)), 4.9)
        );
        menuRepository.saveAll(menus);

        List<DeliveryOrderItemEntity> deliveryOrderItems = List.of(
                new DeliveryOrderItemEntity(new BigDecimal("12.99"), 2, menus.get(0)),
                new DeliveryOrderItemEntity(new BigDecimal("15.50"), 1, menus.get(3)),
                new DeliveryOrderItemEntity(new BigDecimal("9.99"), 3, menus.get(2)),
                new DeliveryOrderItemEntity(new BigDecimal("11.75"), 2, menus.get(3)),
                new DeliveryOrderItemEntity(new BigDecimal("7.50"), 4, menus.get(2)),
                new DeliveryOrderItemEntity(new BigDecimal("14.00"), 1, menus.get(2)),
                new DeliveryOrderItemEntity(new BigDecimal("10.00"), 2, menus.get(0)),
                new DeliveryOrderItemEntity(new BigDecimal("8.50"), 5, menus.get(2))
        );
        deliveryOrderItemRepository.saveAll(deliveryOrderItems);

        List<DeliveryOrderEntity> deliveryOrders = List.of(
                new DeliveryOrderEntity("Av. Blaz 011", "John Valencia", LocalDateTime.now(),
                        false, List.of(deliveryOrderItems.get(0), deliveryOrderItems.get(1))),
                new DeliveryOrderEntity("Usera 103 P01 ", "Miguel Perez", LocalDateTime.now(),
                        true, List.of(deliveryOrderItems.get(2))),
                new DeliveryOrderEntity("Gran via 001", "Alicia Ramos", LocalDateTime.now(),
                        false, List.of(deliveryOrderItems.get(3), deliveryOrderItems.get(4))),
                new DeliveryOrderEntity("Gran via 002", "Bob Marley", LocalDateTime.now(),
                        false, List.of(deliveryOrderItems.get(5), deliveryOrderItems.get(6))),
                new DeliveryOrderEntity("Calle A. P2", "Carol Gutierrez", LocalDateTime.now(),
                        true, List.of(deliveryOrderItems.get(1), deliveryOrderItems.get(2)))
        );
        deliveryOrderRepository.saveAll(deliveryOrders);
    }

    public void deleteAll() {
        deliveryOrderRepository.deleteAll();
        deliveryOrderItemRepository.deleteAll();
        menuRepository.deleteAll();
        menuCategoryRepository.deleteAll();
    }

}
