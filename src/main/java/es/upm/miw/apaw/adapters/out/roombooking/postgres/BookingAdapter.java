package es.upm.miw.apaw.adapters.out.roombooking.postgres;

import es.upm.miw.apaw.domain.ports.out.roombooking.BookingGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookingAdapter implements BookingGateway {

    private final BookingRepository bookingRepository;
}