package es.upm.miw.apaw_practice.domain.models.military;

public class SoldierRankUpdating {
    private String identityDocument;
    private String rank;

    public SoldierRankUpdating() {
        //empty for framework
    }

    public SoldierRankUpdating(String identityDocument, String rank) {
        this.identityDocument = identityDocument;
        this.rank = rank;
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = identityDocument;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "SoldierRankUpdating{" +
                "identityDocument='" + identityDocument + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}
