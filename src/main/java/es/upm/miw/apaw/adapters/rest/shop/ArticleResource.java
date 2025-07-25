package es.upm.miw.apaw.adapters.rest.shop;

import es.upm.miw.apaw.domain.models.shop.Article;
import es.upm.miw.apaw.domain.models.shop.ArticlePriceUpdating;
import es.upm.miw.apaw.domain.services.shop.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(ArticleResource.ARTICLES)
public class ArticleResource {
    public static final String ARTICLES = "/shop/articles";
    private final ArticleService articleService;

    @Autowired
    public ArticleResource(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public Article create(@Valid @RequestBody Article article) {
        article.doDefault();
        return this.articleService.create(article);
    }

    @PatchMapping
    public void updatePrices(@RequestBody List<ArticlePriceUpdating> articlePriceUpdatingList) {
        this.articleService.updatePrices(articlePriceUpdatingList.stream());
    }

    @GetMapping
    public Stream<Article> findByProviderAndPriceGreaterThan(@RequestParam String provider, @RequestParam BigDecimal price) {
        return this.articleService.findByProviderAndPriceGreaterThan(provider, price);
    }

}
