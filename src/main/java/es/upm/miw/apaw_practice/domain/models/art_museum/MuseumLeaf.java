package es.upm.miw.apaw_practice.domain.models.art_museum;

import java.util.stream.Stream;

public class MuseumLeaf extends MuseumComponent {
    private final Museum museum;

    public MuseumLeaf(Museum museum) {
        this.museum = museum;
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(MuseumComponent museumComponent) {
        throw new UnsupportedOperationException("Unsupported operation: not composite");
    }

    @Override
    public void remove(MuseumComponent museumComponent) {
        throw new UnsupportedOperationException("Unsupported operation: not composite");
    }

    @Override
    public Stream<String> getName() {
        return Stream.of(museum.getName());
    }

    @Override
    public Stream<Integer> getCapacity() {
        return Stream.of(museum.getCapacity());
    }

    @Override
    public Stream<Boolean> getOpen() {
        return Stream.of(museum.getOpen());
    }

    @Override
    public Stream<Artwork> getArtworks() {
        return museum.getArtworks().stream();
    }

    @Override
    public Stream<Exhibition> getExhibitions() {
        return museum.getExhibitions().stream();
    }
}
