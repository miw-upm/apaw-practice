package es.upm.miw.apawpractice.adapters.rest.shop;

import es.upm.miw.apawpractice.domain.models.shop.Article;
import es.upm.miw.apawpractice.domain.models.shop.ArticlePriceUpdating;
import es.upm.miw.apawpractice.domain.services.shop.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(ArticleResource.ARTICLES)
public class ArticleResource {
    public static final String ARTICLES = "/shop/articles";

    public static final String SEARCH = "/search";

    private final ArticleService articleService;

    @Autowired
    public ArticleResource(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public Article create(@RequestBody Article article) {
        article.doDefault();
        return this.articleService.create(article);
    }

    @PatchMapping
    public void updatePrices(@RequestBody List<ArticlePriceUpdating> articlePriceUpdatingList) {
        this.articleService.updatePrices(articlePriceUpdatingList.stream());
    }

    @GetMapping(SEARCH)
    public Stream<Article> findByProviderAndPriceGreaterThan(@RequestParam String provider, @RequestParam BigDecimal price) {
        return this.articleService.findByProviderAndPriceGreaterThan(provider, price);
    }

}
