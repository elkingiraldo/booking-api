package co.com.elkin.apps.bookingapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.elkin.apps.bookingapi.entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
