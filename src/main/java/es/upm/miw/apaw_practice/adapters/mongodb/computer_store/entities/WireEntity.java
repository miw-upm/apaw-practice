package es.upm.miw.apaw_practice.adapters.mongodb.computer_store.entities;

import es.upm.miw.apaw_practice.domain.models.computer_store.Wire;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Document
public class WireEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private BigDecimal length;
    private String jacketMaterial;

    public WireEntity() {
        // empty for framework
    }

    public WireEntity(String name, BigDecimal length, String jacketMaterial) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.length = length;
        this.jacketMaterial = jacketMaterial;
    }

    public WireEntity(Wire wire) {
        BeanUtils.copyProperties(wire, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public String getJacketMaterial() {
        return jacketMaterial;
    }

    public void setJacketMaterial(String jacketMaterial) {
        this.jacketMaterial = jacketMaterial;
    }

    public Wire toWire() {
        Wire wire = new Wire();
        BeanUtils.copyProperties(this, wire);
        return wire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WireEntity that)) return false;
        return (Objects.equals(getId(), that.getId()) || Objects.equals(getName(), that.getName()))
                && Objects.equals(getLength(), that.getLength())
                && Objects.equals(getJacketMaterial(), that.getJacketMaterial());
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return "WireEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", jacketMaterial='" + jacketMaterial + '\'' +
                '}';
    }
}
