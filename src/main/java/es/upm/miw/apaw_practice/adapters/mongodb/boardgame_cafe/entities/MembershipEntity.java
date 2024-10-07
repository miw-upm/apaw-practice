package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Membership;
import org.springframework.beans.BeanUtils;
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

    public MembershipEntity( Membership membership) {
        this.fromMembership(membership);
    }

    public void fromMembership(Membership membership){
        BeanUtils.copyProperties(membership, this);
    }

    public Membership toMembership(){
        Membership membership = new Membership();
        BeanUtils.copyProperties(this, membership);
        return membership;
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