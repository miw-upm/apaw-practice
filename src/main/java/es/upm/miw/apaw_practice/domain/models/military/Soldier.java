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
