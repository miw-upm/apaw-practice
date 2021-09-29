package es.upm.miw.apaw_practice.domain.models.cinema;

public class Film {
    private String barcode;
    private String name;
    private String description;

    public Film() {
        //empty for framework
    }

    public Film(String barcode, String name, String description) {
        this.barcode = barcode;
        this.name = name;
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Film{" +
                "barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
