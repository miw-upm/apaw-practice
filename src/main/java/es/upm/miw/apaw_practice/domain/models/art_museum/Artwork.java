package es.upm.miw.apaw_practice.domain.models.art_museum;

public class Artwork {
    private String inventoryNumber;
    private String titleName;
    private Integer year;
    private Artist artist;

    public Artwork() {
        // empty for framework
    }

    public Artwork(String inventoryNumber, String titleName, Integer year, Artist artist) {
        this.inventoryNumber = inventoryNumber;
        this.titleName = titleName;
        this.year = year;
        this.artist = artist;
    }

    public static Artwork ofInventoryNumber(Artwork artwork) {
        Artwork artworkDto = new Artwork();
        artworkDto.setInventoryNumber(artwork.getInventoryNumber());
        return artworkDto;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Artwork{" +
                "inventoryNumber='" + inventoryNumber + '\'' +
                ", titleName='" + titleName + '\'' +
                ", year=" + year +
                ", artist=" + artist +
                '}';
    }
}
