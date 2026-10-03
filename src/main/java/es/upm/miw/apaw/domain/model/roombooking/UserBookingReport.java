package es.upm.miw.apaw.domain.model.roombooking;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBookingReport {
    private UserSnapshot userSnapshot;
    private Long totalBookings;
    private Long totalAttendees;
}