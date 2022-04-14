package developers.scasystem.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<patient, Long> {
	Optional<patient> findByInsuranceNumber(int InsuranceNumber);
	
	Boolean existsByInsuranceNumber(int InsuranceNumber);
	

}
