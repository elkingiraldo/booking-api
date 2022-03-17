package co.com.elkin.apps.bookingapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.elkin.apps.bookingapi.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
