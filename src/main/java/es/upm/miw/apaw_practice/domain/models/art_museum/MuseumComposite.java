package es.upm.miw.apaw_practice.domain.models.art_museum;

import java.util.List;
import java.util.stream.Stream;

public class MuseumComposite extends MuseumComponent {
    private final List<MuseumComponent> museumList;

    public MuseumComposite() {
        this.museumList = new java.util.ArrayList<>();
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(MuseumComponent museumComponent) {
        this.museumList.add(museumComponent);
    }

    @Override
    public void remove(MuseumComponent museumComponent) {
        this.museumList.remove(museumComponent);
    }

    @Override
    public Stream<String> getName() {
        return museumList.stream()
                .flatMap(MuseumComponent::getName);
    }

    @Override
    public Stream<Integer> getCapacity() {
        return museumList.stream()
                .flatMap(MuseumComponent::getCapacity);
    }

    @Override
    public Stream<Boolean> getOpen() {
        return museumList.stream()
                .flatMap(MuseumComponent::getOpen);
    }

    @Override
    public Stream<Artwork> getArtworks() {
        return museumList.stream()
                .flatMap(MuseumComponent::getArtworks);
    }

    @Override
    public Stream<Exhibition> getExhibitions() {
        return museumList.stream()
                .flatMap(MuseumComponent::getExhibitions);
    }
}
