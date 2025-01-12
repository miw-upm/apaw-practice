package es.upm.miw.apaw_practice.adapters.rest.hotel;


import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import es.upm.miw.apaw_practice.domain.services.hotel.HotelReservationService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(HotelReservationResource.RESERVATIONS)
public class HotelReservationResource {
    static final String RESERVATIONS = "/reservation/reservations";
    static final String NUMBERS = "/{reservationNumber}";

    private final HotelReservationService hotelReservationService;

    public HotelReservationResource(HotelReservationService hotelReservationService){
        this.hotelReservationService = hotelReservationService;
    }

    @PatchMapping(NUMBERS)
    public HotelReservation patchReservetion(@PathVariable String reservationNumber, @RequestBody HotelReservation reservation) {

        return this.hotelReservationService.patchReservation(reservationNumber, reservation);
    }
}
