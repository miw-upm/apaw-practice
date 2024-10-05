package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class MembershipEntity {
    @Id
    private Integer membershipId;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;

    public MembershipEntity() {
        //empty for framework
    }

    public MembershipEntity(Integer membershipId, String type, LocalDate startDate, LocalDate endDate) {
        this.membershipId = membershipId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
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
               ", startDate=" + startDate +
               ", endDate=" + endDate +
               '}';
    }
}