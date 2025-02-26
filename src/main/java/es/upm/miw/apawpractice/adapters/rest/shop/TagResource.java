package es.upm.miw.apawpractice.adapters.rest.shop;

import es.upm.miw.apawpractice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apawpractice.domain.exceptions.BadRequestException;
import es.upm.miw.apawpractice.domain.models.shop.Tag;
import es.upm.miw.apawpractice.domain.services.shop.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(TagResource.TAGS)
public class TagResource {
    public static final String TAGS = "/shop/tags";

    public static final String NAME_ID = "/{name}";
    public static final String SEARCH = "/search";

    private final TagService tagService;

    @Autowired
    public TagResource(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping(NAME_ID)
    public Tag read(@PathVariable String name) {
        return Tag.ofArticleBarcode(this.tagService.read(name));
    }

    @DeleteMapping(NAME_ID)
    public void delete(@PathVariable String name) {
        this.tagService.delete(name);
    }

    @GetMapping(SEARCH)
    public Stream<Tag> findByArticlesInShoppingCarts(@RequestParam String q) {
        if (!"in".equals(new LexicalAnalyzer().extractWithAssure(q, "shopping-carts"))) {
            throw new BadRequestException("q incorrect, expected in");
        }
        return this.tagService.findByArticlesInShoppingCarts();
    }
}
