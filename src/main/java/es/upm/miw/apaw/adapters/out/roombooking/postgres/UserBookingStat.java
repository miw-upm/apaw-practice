package es.upm.miw.apaw.adapters.out.roombooking.postgres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBookingStat {
    private UUID userId;
    private Long totalBookings;
    private Long totalAttendees;
}