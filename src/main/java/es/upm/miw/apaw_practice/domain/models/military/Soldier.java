package es.upm.miw.apaw_practice.domain.models.military;

import java.time.LocalDate;

public class Soldier {
    private String identityDocument;
    private String fullName;
    private String rank;
    private LocalDate birthDate;

    public Soldier() {
        // empty for framework
    }

    public Soldier(String identityDocument, String fullName, String rank, LocalDate birthDate) {
        this.identityDocument = identityDocument;
        this.fullName = fullName;
        this.rank = rank;
        this.birthDate = birthDate;
    }

    public static SoldierBuilders.IdentityDocument builder() { return new Builder(); }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = identityDocument;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public static class Builder implements SoldierBuilders.IdentityDocument, SoldierBuilders.FullName, SoldierBuilders.Rank, SoldierBuilders.Optionals {
        private final Soldier soldier;

        public Builder() { this.soldier = new Soldier(); }

        @Override
        public SoldierBuilders.FullName identityDocument(String identityDocument) {
            this.soldier.identityDocument = identityDocument;
            return this;
        }

        @Override
        public SoldierBuilders.Rank fullName(String fullName) {
            this.soldier.fullName = fullName;
            return this;
        }

        @Override
        public SoldierBuilders.Optionals rank(String rank) {
            this.soldier.rank = rank;
            return this;
        }

        @Override
        public SoldierBuilders.Optionals birthDate(LocalDate birthDate) {
            this.soldier.birthDate = birthDate;
            return this;
        }

        @Override
        public Soldier build() { return this.soldier; }
    }

    @Override
    public int hashCode() {
        return this.identityDocument.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (identityDocument.equals(((Soldier) obj).identityDocument));
    }

    @Override
    public String toString() {
        return "Soldier{" +
                "identityDocument='" + identityDocument + '\'' +
                ", fullName='" + fullName + '\'' +
                ", rank='" + rank + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
