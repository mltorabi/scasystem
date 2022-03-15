package developers.scasystem.model;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
	List<Speciality> findByspeciality(String speciality);
}
