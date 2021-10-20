package es.upm.miw.apaw_practice.domain.services.tennis_courts;

import es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts.ReservationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationPersistence reservationPersistence;

    @Autowired
    public ReservationService(ReservationPersistence reservationPersistence){
        this.reservationPersistence = reservationPersistence;
    }

    public void delete(String ownerName, String stringDate, String stringTime){
        LocalDateTime date = ReservationService.extractDateFromString(stringDate, stringTime);
        this.reservationPersistence.delete(ownerName, date);
    }

    public static LocalDateTime extractDateFromString(String date, String time){
        int[] dateValues;
        int[] timeValues;

        dateValues = ReservationService.extractValuesFromString(date);
        timeValues = ReservationService.extractValuesFromString(time);

        return LocalDateTime.of(2000 + dateValues[2], dateValues[1], dateValues[0], timeValues[0], timeValues[1]);
    }

    public static int[] extractValuesFromString(String str){
        int index = 0;
        int nextIndex;
        List<Integer> values = new ArrayList<>();

        int i = 0;
        while(str.indexOf(":", index) > 0){
            nextIndex = str.indexOf(":", index);
            values.add(Integer.parseInt(str.substring(index, nextIndex)));
            index = nextIndex + 1;
        }
        values.add(Integer.parseInt(str.substring(index)));

        return values.stream().mapToInt(Integer::intValue).toArray();
    }
}
