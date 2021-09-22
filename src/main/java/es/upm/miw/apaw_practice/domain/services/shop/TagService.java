package es.upm.miw.apaw_practice.domain.services.shop;

import es.upm.miw.apaw_practice.domain.models.shop.Article;
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

    private final TagPersistence tagPersistence;

    private final ShoppingCartPersistence shoppingCartPersistence;

    @Autowired
    public TagService(TagPersistence tagPersistence, ShoppingCartPersistence shoppingCartPersistence) {
        this.tagPersistence = tagPersistence;
        this.shoppingCartPersistence = shoppingCartPersistence;
    }

    public Tag read(String name) {
        return this.tagPersistence.readByName(name);
    }

    public void delete(String name) {
        this.tagPersistence.delete(name);
    }

    public Stream<Tag> findByArticlesInShoppingCarts() {
        List<String> barcodes = this.shoppingCartPersistence.readAll()
                .flatMap(shoppingCart -> shoppingCart.getArticleItems().stream())
                .map(ArticleItem::getArticle)
                .map(Article::getBarcode)
                .distinct()
                .collect(Collectors.toList());
        return this.tagPersistence.readAll()
                .filter(tag -> tag.getArticles().stream()
                        .map(Article::getBarcode)
                        .anyMatch(barcodes::contains));
    }
}
