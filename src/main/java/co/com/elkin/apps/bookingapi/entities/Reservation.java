package co.com.elkin.apps.bookingapi.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
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

	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
	private Set<RoomReserved> roomReserved;
}
