package co.com.elkin.apps.bookingapi.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "ROOM_TBL")
public class RoomReserved implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private float price;

	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
}
