package developers.scasystem.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
	List<Speciality> findBySpecialityTitle(String speciality);
}
