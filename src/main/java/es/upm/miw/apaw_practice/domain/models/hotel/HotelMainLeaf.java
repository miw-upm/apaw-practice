package es.upm.miw.apaw_practice.domain.models.hotel;

import java.util.stream.Stream;

public class HotelMainLeaf extends HotelMainComponent {

    private final HotelMain hotel;

    public HotelMainLeaf(HotelMain hotel) {
        this.hotel = hotel;
    }

    @Override
    public void add(HotelMainComponent hotelMainComponent) {
        throw new UnsupportedOperationException("Unsupported operation: not composite");
    }

    @Override
    public void remove(HotelMainComponent hotelMainComponent) {
        throw new UnsupportedOperationException("Unsupported operation: not composite");
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public Stream<String> getName() {
        return Stream.of(hotel.getName());
    }

    @Override
    public Stream<String> getAddress() {
        return Stream.of(hotel.getAddress());
    }

    @Override
    public Stream<String> getPhone() {
        return Stream.of(hotel.getPhone());
    }

    @Override
    public Stream<HotelRoom> getRooms(){ return hotel.getRooms().stream(); }

    @Override
    public Stream<HotelClient> getClients(){ return hotel.getClients().stream(); }
}
