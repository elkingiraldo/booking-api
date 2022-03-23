package co.com.elkin.apps.bookingapi.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.elkin.apps.bookingapi.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

	List<Reservation> findAllByStartDateGreaterThanAndEndDateLessThanEqual(final Date startDate, final Date endDate);
}
