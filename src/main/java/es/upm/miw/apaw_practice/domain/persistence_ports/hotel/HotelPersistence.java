package es.upm.miw.apaw_practice.domain.persistence_ports.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HotelPersistence {
    Hotel readByName(String name);

    Double MaxParticipantAverageByEmail(String email);

    List<Integer> distinctRoomNumbersByInstructor(String instructorName);
}
