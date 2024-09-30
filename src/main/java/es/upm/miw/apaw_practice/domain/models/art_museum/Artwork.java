package es.upm.miw.apaw_practice.domain.models.art_museum;

public class Artwork {
    private String id;
    private String titleName;
    private int year;

    public Artwork() {
        // empty for framework
    }

    public Artwork(String id, String titleName, int year) {
        this.id = id;
        this.titleName = titleName;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Artwork{" +
                "id='" + id + '\'' +
                ", titleName='" + titleName + '\'' +
                ", year=" + year +
                '}';
    }
}
