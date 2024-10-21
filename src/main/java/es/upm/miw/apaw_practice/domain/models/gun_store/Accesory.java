package es.upm.miw.apaw_practice.domain.models.gun_store;

import java.math.BigDecimal;
import java.util.Objects;

public class Accesory {
    private Integer accesoryId;
    private String category;
    private BigDecimal price;

    public Accesory() {
        //Empty for framework
    }

    public Accesory(int accesoryId, String category, BigDecimal price) {
        this.accesoryId = accesoryId;
        this.category = category;
        this.price = price;
    }

    public static AccesoryBuilders.AccesoryId builder() {
        return new Builder();
    }

    public int getAccesoryId() {
        return accesoryId;
    }

    public void setAccesoryId(int accesoryId) {
        this.accesoryId = accesoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Accesory accesory)) return false;
        return Objects.equals(accesoryId, accesory.accesoryId) && Objects.equals(category, accesory.category)
                && Objects.equals(price, accesory.price);
    }

    public static class Builder implements AccesoryBuilders.AccesoryId, AccesoryBuilders.Price
            , AccesoryBuilders.Category, AccesoryBuilders.Builder {
        private final Accesory instance;

        private Builder() {
            instance = new Accesory();
        }

        @Override
        public AccesoryBuilders.Category accesoryId(Integer accesoryId) {
            instance.accesoryId = accesoryId;
            return this;
        }

        @Override
        public AccesoryBuilders.Price category(String category) {
            instance.category = category;
            return this;
        }

        @Override
        public AccesoryBuilders.Builder price(BigDecimal price) {
            instance.price = price;
            return this;
        }

        @Override
        public Accesory build() {
            return instance;
        }

    }
}
