package es.upm.miw.apaw_practice.domain.models.cinema;


public abstract class MovieComponent {
    public void add(MovieComponent movieComponent) {
        throw new UnsupportedOperationException("Operation not supported");
    }
    public void remove(MovieComponent movieComponent) {
        throw new UnsupportedOperationException("Operation not supported");
    }
    public MovieComponent getChild(int index) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    public abstract String getTitle();
    public abstract boolean isThreeDFormat();
}