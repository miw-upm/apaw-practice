package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
public class MembershipEntity {
    @Id
    private Integer membershipId;
    private String type;
    private Integer duration;
    private BigDecimal discount;

    public MembershipEntity() {
        //empty for framework
    }

    public MembershipEntity(Integer membershipId, String type, Integer duration, BigDecimal discount) {
        this.membershipId = membershipId;
        this.type = type;
        this.duration = duration;
        this.discount = discount;
    }

    public Integer getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Integer membershipId) {
        this.membershipId = membershipId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public int hashCode() {
        return membershipId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (membershipId.equals(((MembershipEntity) obj).membershipId));
    }

    @Override
    public String toString() {
        return "MembershipEntity{" +
                "membershipId=" + membershipId +
                ", type='" + type + '\'' +
                ", duration=" + duration +
                ", discount=" + discount +
                '}';
    }
}