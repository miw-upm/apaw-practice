package es.upm.miw.apaw_practice.domain.models.zoo;

public class CageCaretakerSurname {

    private String cageLocationCode;
    private String caretakerSurname;

    public CageCaretakerSurname() {
        //empty from framework
    }

    public CageCaretakerSurname(String cageLocationCode, String caretakerSurname) {
        this.cageLocationCode = cageLocationCode;
        this.caretakerSurname = caretakerSurname;
    }

    public String getCageLocationCode() {
        return cageLocationCode;
    }

    public void setCageLocationCode(String cageLocationCode) {
        this.cageLocationCode = cageLocationCode;
    }

    public String getCaretakerSurname() {
        return caretakerSurname;
    }

    public void setCaretakerSurname(String caretakerSurname) {
        this.caretakerSurname = caretakerSurname;
    }

    @Override
    public String toString() {
        return "CageCaretakerSurname{" +
                "cageLocationCode='" + cageLocationCode +
                ", caretakerSurname='" + caretakerSurname +
                '}';
    }
}
