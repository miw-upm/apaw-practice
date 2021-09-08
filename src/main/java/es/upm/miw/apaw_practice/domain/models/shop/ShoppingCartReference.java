package es.upm.miw.apaw_practice.domain.models.shop;

public class ShoppingCartReference {

    private String id;
    private String user;

    public ShoppingCartReference() {
        //empty for framework
    }

    public ShoppingCartReference(ShoppingCart shoppingCart) {
        this.id = shoppingCart.getId();
        this.user = shoppingCart.getUser();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ShoppingCartReference{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
