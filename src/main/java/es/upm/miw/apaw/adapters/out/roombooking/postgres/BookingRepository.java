package es.upm.miw.apaw.adapters.out.roombooking.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
    boolean existsByRoomId(UUID roomId);
}