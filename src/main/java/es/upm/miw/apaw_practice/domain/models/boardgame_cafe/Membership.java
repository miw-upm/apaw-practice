package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import java.math.BigDecimal;
import java.util.Objects;

public class Membership {
    private Integer membershipId;
    private String type;
    private Integer duration;
    private BigDecimal discount;

    public Membership() {
        //empty for framework
    }

    public Membership(Integer membershipId, String type, Integer duration, BigDecimal discount) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membership that = (Membership) o;
        return Objects.equals(membershipId, that.membershipId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(membershipId, type, duration, discount);
    }

    @Override
    public String toString() {
        return "Membership{" +
                "membershipId=" + membershipId +
                ", type='" + type + '\'' +
                ", duration=" + duration +
                ", discount=" + discount +
                '}';
    }
}