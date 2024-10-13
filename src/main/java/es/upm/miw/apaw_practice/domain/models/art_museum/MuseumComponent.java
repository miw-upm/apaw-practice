package es.upm.miw.apaw_practice.domain.models.art_museum;

import java.util.stream.Stream;

public abstract class MuseumComponent {

    public abstract boolean isComposite();

    public abstract void add(MuseumComponent museumComponent);

    public abstract void remove(MuseumComponent museumComponent);

    public abstract Stream<String> getName();

    public abstract Stream<Integer> getCapacity();

    public abstract Stream<Boolean> getOpen();

    public abstract Stream<Artwork> getArtworks();

    public abstract Stream<Exhibition> getExhibitions();
}
