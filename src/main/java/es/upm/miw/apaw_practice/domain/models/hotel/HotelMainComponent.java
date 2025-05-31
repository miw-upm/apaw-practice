package es.upm.miw.apaw_practice.domain.models.hotel;

import java.util.stream.Stream;

public abstract class HotelMainComponent {

    public abstract void add(HotelMainComponent hotelMainComponent);

    public abstract void remove(HotelMainComponent hotelMainComponent);

    public abstract boolean isComposite();

    public abstract Stream<String> getName();

    public abstract Stream<String> getAddress();

    public abstract Stream<String> getPhone();

    public abstract Stream<HotelRoom> getRooms();

    public abstract Stream<HotelClient> getClients();

}
