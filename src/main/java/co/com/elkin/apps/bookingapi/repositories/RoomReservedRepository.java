package co.com.elkin.apps.bookingapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.elkin.apps.bookingapi.entities.RoomReserved;

@Repository
public interface RoomReservedRepository extends JpaRepository<RoomReserved, Integer> {
}
