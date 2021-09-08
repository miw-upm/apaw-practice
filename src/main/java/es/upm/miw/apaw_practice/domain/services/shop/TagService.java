package es.upm.miw.apaw_practice.domain.services.shop;

import es.upm.miw.apaw_practice.domain.models.shop.ArticleItem;
import es.upm.miw.apaw_practice.domain.models.shop.Tag;
import es.upm.miw.apaw_practice.domain.persistence_ports.shop.ShoppingCartPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.shop.TagPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TagService {

    private TagPersistence tagPersistence;

    private ShoppingCartPersistence shoppingCartPersistence;

    @Autowired
    public TagService(TagPersistence tagPersistence, ShoppingCartPersistence shoppingCartPersistence) {
        this.tagPersistence = tagPersistence;
        this.shoppingCartPersistence = shoppingCartPersistence;
    }

    public Tag read(String id) {
        return this.tagPersistence.readById(id);
    }

    public void delete(String id) {
        this.tagPersistence.deleteById(id);
    }

    public Stream<Tag> findByArticlesInShoppingCarts() {
        List<Long> barcodes = this.shoppingCartPersistence.readAll()
                .flatMap(shoppingCart -> shoppingCart.getArticleItems().stream())
                .map(ArticleItem::getBarcode)
                .collect(Collectors.toList());
        return this.tagPersistence.readAll()
                .filter(tag -> tag.getArticlesBarcode().stream().anyMatch(barcodes::contains));
    }
}
