package es.upm.miw.apaw_practice.domain.models.tennis_courts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private String ownerName;
    private LocalDateTime date;
    private Integer duration;
    private Court court;
    private List<Player> players;

    public static Builder builder(LocalDateTime date, Integer duration){
        return new Builder(date, duration);
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String name) {
        this.ownerName = name;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Court getCourt() {
        return this.court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "ownerName='" + this.ownerName + '\'' +
                ", date=" + this.date +
                ", duration=" + this.duration +
                ", court=" + this.court +
                ", players=" + this.players +
                '}';
    }

    public static class Builder {
        private Reservation reservation;

        public Builder(LocalDateTime date, Integer duration){
            this.reservation = new Reservation();
            this.reservation.date = date;
            this.reservation.duration = duration;
        }

        public Builder name(String name){
            this.reservation.ownerName = name;
            return this;
        }

        public Builder court(Court court){
            this.reservation.court = court;
            return this;
        }

        public Builder player(Player player){
            if(this.reservation.players == null){
                this.reservation.players = new ArrayList<>();
            }
            this.reservation.players.add(player);
            return this;
        }
    }
}
