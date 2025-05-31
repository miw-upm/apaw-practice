package es.upm.miw.apaw_practice.domain.models.hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class HotelMainComposite extends HotelMainComponent {
    private final List<HotelMainComponent> hotelMainComponents;

    public HotelMainComposite() {
        this.hotelMainComponents = new ArrayList<>();
    }

    @Override
    public void add(HotelMainComponent hotelMainComponent) {
        this.hotelMainComponents.add(hotelMainComponent);
    }

    @Override
    public void remove(HotelMainComponent hotelMainComponent) {
        this.hotelMainComponents.remove(hotelMainComponent);
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public Stream<String> getName() {
        return hotelMainComponents
                .stream()
                .flatMap(HotelMainComponent::getName);
    }

    @Override
    public Stream<String> getAddress() {
        return hotelMainComponents
                .stream()
                .flatMap(HotelMainComponent::getAddress);
    }

    @Override
    public Stream<String> getPhone() {
        return hotelMainComponents
                .stream()
                .flatMap(HotelMainComponent::getPhone);
    }
    @Override
    public Stream<HotelRoom> getRooms() {
        return hotelMainComponents
                .stream()
                .flatMap(HotelMainComponent::getRooms);
    }
    @Override
    public Stream<HotelClient> getClients() {
        return hotelMainComponents
                .stream()
                .flatMap(HotelMainComponent::getClients);
    }

}
