package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import java.time.LocalDate;

public class Membership {
    private int membershipId;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;

    public Membership() {
        //empty for framework
    }

    public Membership(int membershipId, String type, LocalDate startDate, LocalDate endDate) {
        this.membershipId = membershipId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
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

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "membershipId=" + membershipId +
                ", type='" + type + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}