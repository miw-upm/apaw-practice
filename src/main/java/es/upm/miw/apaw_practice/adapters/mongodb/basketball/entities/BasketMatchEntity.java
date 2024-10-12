package es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document
public class BasketMatchEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private Integer matchId;
    private LocalDateTime date;
    private String address;
    @DBRef
    private List<BasketPlayerEntity> basketPlayers;

    public BasketMatchEntity() {
        // empty for framework
    }

    public BasketMatchEntity(Integer matchId, LocalDateTime date, String address, List<BasketPlayerEntity> basketPlayers) {
        this.id = UUID.randomUUID().toString();
        this.matchId = matchId;
        this.date = date;
        this.address = address;
        this.basketPlayers = basketPlayers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<BasketPlayerEntity> getBasketPlayers() {
        return basketPlayers;
    }

    public void setBasketPlayers(List<BasketPlayerEntity> basketPlayers) {
        this.basketPlayers = basketPlayers;
    }

    @Override
    public int hashCode() {
        return matchId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && matchId.equals(((BasketMatchEntity) obj).matchId));
    }

    @Override
    public String toString() {
        return "BasketMatchEntity{" +
                "id='" + id + '\'' +
                ", matchId=" + matchId +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", basketPlayers=" + basketPlayers +
                '}';
    }
}