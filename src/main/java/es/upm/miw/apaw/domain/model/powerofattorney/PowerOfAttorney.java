package es.upm.miw.apaw.domain.model.powerofattorney;

import java.time.LocalDate;
import java.util.UUID;

public class PowerOfAttorney {

    private final UUID id;
    private String protocolNumber;
    private LocalDate grantDate;
    private LocalDate expirationDate;
    private String scope;
    private String limitations;
    private String notaryName;
    private String notaryOffice;
    private String notes;
    private PowerOfAttorneyParty principal;
    private PowerOfAttorneyParty attorney;
    private PowerOfAttorneyType type;
    private PowerOfAttorneyStatus status;

    public PowerOfAttorney(
            String protocolNumber,
            LocalDate grantDate,
            LocalDate expirationDate,
            String scope,
            String limitations,
            String notaryName,
            String notaryOffice,
            String notes,
            PowerOfAttorneyParty principal,
            PowerOfAttorneyParty attorney) {
        this.id = UUID.randomUUID();
        this.protocolNumber = protocolNumber;
        this.grantDate = grantDate;
        this.expirationDate = expirationDate;
        this.scope = scope;
        this.limitations = limitations;
        this.notaryName = notaryName;
        this.notaryOffice = notaryOffice;
        this.notes = notes;
        this.principal = principal;
        this.attorney = attorney;
        this.type = PowerOfAttorneyType.GENERAL;
        this.status = PowerOfAttorneyStatus.ACTIVE;
    }

    public UUID getId() {
        return this.id;
    }

    public String getProtocolNumber() {
        return this.protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public LocalDate getGrantDate() {
        return this.grantDate;
    }

    public void setGrantDate(LocalDate grantDate) {
        this.grantDate = grantDate;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getLimitations() {
        return this.limitations;
    }

    public void setLimitations(String limitations) {
        this.limitations = limitations;
    }

    public String getNotaryName() {
        return this.notaryName;
    }

    public void setNotaryName(String notaryName) {
        this.notaryName = notaryName;
    }

    public String getNotaryOffice() {
        return this.notaryOffice;
    }

    public void setNotaryOffice(String notaryOffice) {
        this.notaryOffice = notaryOffice;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public PowerOfAttorneyParty getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(PowerOfAttorneyParty principal) {
        this.principal = principal;
    }

    public PowerOfAttorneyParty getAttorney() {
        return this.attorney;
    }

    public void setAttorney(PowerOfAttorneyParty attorney) {
        this.attorney = attorney;
    }

    public PowerOfAttorneyType getType() {
        return this.type;
    }

    public void setType(PowerOfAttorneyType type) {
        this.type = type;
    }

    public PowerOfAttorneyStatus getStatus() {
        return this.status;
    }

    public void setStatus(PowerOfAttorneyStatus status) {
        this.status = status;
    }
}