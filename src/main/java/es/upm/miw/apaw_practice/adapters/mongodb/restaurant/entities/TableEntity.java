package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities;

import es.upm.miw.apaw_practice.domain.models.restaurant.Table;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Document
public class TableEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private Integer number;
    private Boolean occupied;
    private String style;
    private BigDecimal price;
    private List<ReserveEntity> reserves;

    public TableEntity(){
        //empty for framework
    }

    public TableEntity(Integer number, Boolean occupied, String style, BigDecimal price, List<ReserveEntity> reserves) {
        this.id = UUID.randomUUID().toString();
        this.number = number;
        this.occupied = occupied;
        this.style = style;
        this.price = price;
        this.reserves = reserves;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ReserveEntity> getReserves() {
        return reserves;
    }

    public void setReserves(List<ReserveEntity> reserves) {
        this.reserves = reserves;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((TableEntity) obj).id));
    }

    @Override
    public String toString() {
        return "TableEntity{" +
                "id='" + id + '\'' +
                ", occupied=" + occupied +
                ", number=" + number +
                ", style='" + style + '\'' +
                ", price=" + price +
                ", reserves=" + reserves +
                '}';
    }

    public Table toTable() {
        Table table = new Table();
        BeanUtils.copyProperties(this,table);
        return table;
    }

    public void fromTable(Table table) {
        BeanUtils.copyProperties(table,this);
    }
}
