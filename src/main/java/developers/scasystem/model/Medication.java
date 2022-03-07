package developers.scasystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "Medications")
public class Medication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "Title")
	private String medTitle;
	
	@Column(name = "Uses")
	private String medUses;
	
	@Column(name = "sideEffect")
	private String medSideEffect;
	
	@Column(name = "Overdose")
	private String medOverdose;
	
	
	
	
	public Medication() {
		
	}
	
	public Medication(String MedTitle, String MedUses, String SideEffects, String Medoverdose) {
		this.medTitle = MedTitle;
		this.medUses = MedUses ;
		this.medSideEffect = SideEffects;
		this.medOverdose = Medoverdose;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMedTitle() {
		return medTitle;
	}

	public void setMedTitle(String medTitle) {
		this.medTitle = medTitle;
	}

	public String getMedUses() {
		return medUses;
	}

	public void setMedUses(String medUses) {
		this.medUses = medUses;
	}

	public String getMedSideEffect() {
		return medSideEffect;
	}

	public void setMedSideEffect(String medSideEffect) {
		this.medSideEffect = medSideEffect;
	}

	public String getMedOverdose() {
		return medOverdose;
	}

	public void setMedOverdose(String medOverdose) {
		this.medOverdose = medOverdose;
	}


	
	
	
}

