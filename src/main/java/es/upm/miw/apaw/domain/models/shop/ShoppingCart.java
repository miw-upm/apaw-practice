package es.upm.miw.apaw.domain.models.shop;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    private UUID id;
    private LocalDateTime creationDate;
    @NotNull
    @NotEmpty
    private List<ArticleItem> articleItems;
    @NotNull
    private UserDto user;

    public static ShoppingCart ofIdUser(ShoppingCart shoppingCart) {
        ShoppingCart shoppingCartDto = new ShoppingCart();
        shoppingCartDto.setId(shoppingCart.getId());
        shoppingCartDto.setUser(shoppingCart.getUser());
        return shoppingCartDto;
    }

}
