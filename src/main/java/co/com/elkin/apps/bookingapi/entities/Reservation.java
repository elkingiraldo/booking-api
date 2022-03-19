package co.com.elkin.apps.bookingapi.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.elkin.apps.bookingapi.enums.ReservationStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "RESERVATION_TBL")
public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private Date startDate;
	private Date endDate;
	private Timestamp tsCreated;
	private Timestamp tsUpdated;
	private float totalPrice;
	private boolean deleted;

	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
	private Set<RoomReserved> roomReserved;
}
