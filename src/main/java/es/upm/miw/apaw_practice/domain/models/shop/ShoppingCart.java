package es.upm.miw.apaw_practice.domain.models.shop;

import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCart {
    private String id;
    private LocalDateTime creationDate;
    private List<ArticleItem> articleItems;
    private String user;
    private String address;

    public ShoppingCart() {
        //empty from framework
    }

    public static ShoppingCart ofIdUser(ShoppingCart shoppingCart) {
        ShoppingCart shoppingCartDto = new ShoppingCart();
        shoppingCartDto.setId(shoppingCart.getId());
        shoppingCartDto.setUser(shoppingCart.getUser());
        return shoppingCartDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<ArticleItem> getArticleItems() {
        return articleItems;
    }

    public void setArticleItems(List<ArticleItem> articleItems) {
        this.articleItems = articleItems;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((ShoppingCart) obj).id));
    }

    @Override
    public String toString() {
        return "ShoppingCartEntity{" +
                "id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", articleItems=" + articleItems +
                ", user='" + user + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
