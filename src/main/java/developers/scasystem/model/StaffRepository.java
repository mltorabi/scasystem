package developers.scasystem.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	
	Optional<Staff> findByEmail(String email);
}
