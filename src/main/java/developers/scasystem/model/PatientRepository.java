package developers.scasystem.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<patient, Long> {
	List<patient> findByInsurance(int InsuranceNumber);
	List<patient> findByPhone (String Phone);

}
