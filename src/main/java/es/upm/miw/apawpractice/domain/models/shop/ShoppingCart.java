package es.upm.miw.apawpractice.domain.models.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    private String id;
    private LocalDateTime creationDate;
    private List<ArticleItem> articleItems = new ArrayList<>();
    private String user;
    private String address;

    public static ShoppingCart ofIdUser(ShoppingCart shoppingCart) {
        ShoppingCart shoppingCartDto = new ShoppingCart();
        shoppingCartDto.setId(shoppingCart.getId());
        shoppingCartDto.setUser(shoppingCart.getUser());
        return shoppingCartDto;
    }

}
