package es.upm.miw.apaw.adapters.out.roombooking.postgres;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Comparator;
import java.util.List;

import static es.upm.miw.apaw.config.seeders.RoomBookingSeederForDev.USER_ID_0;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class BookingRepositoryIT {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void testFindUserBookingStats() {
        List<UserBookingStat> stats = this.bookingRepository.findUserBookingStats();

        assertThat(stats).extracting(UserBookingStat::getTotalBookings)
                .isSortedAccordingTo(Comparator.reverseOrder());

        assertThat(stats).filteredOn(stat -> stat.getUserId().equals(USER_ID_0))
                .singleElement()
                .satisfies(stat -> {
                    assertThat(stat.getTotalBookings()).isGreaterThanOrEqualTo(1L);
                    assertThat(stat.getTotalAttendees()).isGreaterThanOrEqualTo(50L);
                });
    }
}