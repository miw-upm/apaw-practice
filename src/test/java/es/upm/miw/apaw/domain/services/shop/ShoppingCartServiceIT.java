package es.upm.miw.apaw.domain.services.shop;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.shop.Article;
import es.upm.miw.apaw.domain.models.shop.ArticleItem;
import es.upm.miw.apaw.domain.models.shop.ShoppingCart;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
public class ShoppingCartServiceIT {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testCreate() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation ->
                        UserDto.builder().id(invocation.getArgument(0)).mobile("123456789").firstName("mock").build());

        ArticleItem articleItem = ArticleItem.builder().article(
                Article.builder().barcode("84001").build()).amount(1).discount(BigDecimal.ZERO).build();
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .articleItems(List.of(articleItem))
                .user(UserDto.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002")).build())
                .build();
        ShoppingCart shoppingCartCreated = this.shoppingCartService.create(shoppingCart);

        assertThat(shoppingCartCreated).isNotNull();
        assertThat(shoppingCartCreated.getId()).isNotNull();
        assertThat(shoppingCartCreated.getCreationDate()).isNotNull();
        assertThat(shoppingCartCreated.getUser())
                .isNotNull()
                .extracting(UserDto::getId, UserDto::getMobile, UserDto::getFirstName)
                .containsExactly(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"), "123456789", "mock");
        assertThat(shoppingCartCreated.getArticleItems())
                .hasSize(1)
                .allSatisfy(item -> {
                    assertThat(item.getAmount()).isEqualTo(1);
                    assertThat(item.getDiscount()).isEqualTo(BigDecimal.ZERO);
                    assertThat(item.getArticle()).isNotNull();
                });
    }
}
