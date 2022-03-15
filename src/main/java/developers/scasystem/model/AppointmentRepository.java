package developers.scasystem.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findByRoomNumber(String roomnumber);
}
