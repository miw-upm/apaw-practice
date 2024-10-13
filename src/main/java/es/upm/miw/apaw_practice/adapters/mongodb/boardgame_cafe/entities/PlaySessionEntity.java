package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.PlaySession;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Document
public class PlaySessionEntity {
    @Id
    private Integer playSessionId;
    private Integer groupSize;
    private LocalDateTime sessionDate;
    @DBRef
    private List<GameEntity> selectedGamesEntities;
    @DBRef
    private List<CustomerEntity> customers;

    public PlaySessionEntity() {
        this.selectedGamesEntities = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public PlaySessionEntity(PlaySession playSession) {
        this();
        this.playSessionId = playSession.getPlaySessionId();
        this.groupSize = playSession.getGroupSize();
        this.sessionDate = playSession.getSessionDate();
        playSession.getSelectedGames().forEach(game -> this.selectedGamesEntities.add(new GameEntity(game)));
        playSession.getCustomers().forEach(customer -> this.customers.add(new CustomerEntity(customer)));
    }

    public void fromPlaySession(PlaySession playSession) {
        BeanUtils.copyProperties(playSession, this);
        this.selectedGamesEntities = playSession.getSelectedGames().stream()
                .map(GameEntity::new)
                .collect(Collectors.toList());
        this.customers = playSession.getCustomers().stream()
                .map(CustomerEntity::new)
                .collect(Collectors.toList());
    }

    public PlaySession toPlaySession() {
        PlaySession playSession = new PlaySession();
        BeanUtils.copyProperties(this, playSession);
        playSession.setSelectedGames(this.selectedGamesEntities.stream()
                .map(GameEntity::toGame)
                .collect(Collectors.toList()));
        playSession.setCustomers(this.customers.stream()
                .map(CustomerEntity::toCustomer)
                .collect(Collectors.toList()));
        return playSession;
    }

    public Integer getPlaySessionId() {
        return playSessionId;
    }

    public void setPlaySessionId(Integer playSessionId) {
        this.playSessionId = playSessionId;
    }

    public Integer getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(Integer groupSize) {
        this.groupSize = groupSize;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }

    public List<GameEntity> getSelectedGamesEntities() {
        return selectedGamesEntities;
    }

    public void setSelectedGamesEntities(List<GameEntity> selectedGamesEntities) {
        this.selectedGamesEntities = selectedGamesEntities;
    }

    public List<CustomerEntity> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerEntity> customers) {
        this.customers = customers;
    }

    @Override
    public int hashCode() {
        return playSessionId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (playSessionId.equals(((PlaySessionEntity) obj).playSessionId));
    }

    @Override
    public String toString() {
        return "PlaySessionEntity{" +
                "playSessionId=" + playSessionId +
                ", groupSize=" + groupSize +
                ", sessionDate=" + sessionDate +
                ", selectedGamesEntities=" + selectedGamesEntities +
                ", customers=" + customers +
                '}';
    }
}