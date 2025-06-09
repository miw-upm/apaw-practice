package es.upm.miw.apaw_practice.domain.models.cinema;

// Hoja, película individual
public class MovieLeaf extends MovieComponent {
    private final String title;
    private final boolean threeDFormat;

    public MovieLeaf(String title, boolean threeDFormat) {
        this.title = title;
        this.threeDFormat = threeDFormat;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean isThreeDFormat() {
        return threeDFormat;
    }
}