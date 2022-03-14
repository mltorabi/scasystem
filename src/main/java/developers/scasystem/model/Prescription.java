package developers.scasystem.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prescription")
public class Prescription {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//an appointment may have one prescriptions
	//a prescription has one appointment
	
	@OneToOne(mappedBy = "prescription",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private Appointment appointment;

	//a prescription may have one or more medications
	//a medication may have one or many prescription
	
	@ManyToMany(mappedBy = "prescription", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	private Set<Medication> Medications = new HashSet<>();

	
	@Column(name = "details")
	private String details;

	public Prescription() {
		
	}
	
	public Prescription(Appointment appointmentobj, String details) {
		this.appointment = appointmentobj;
		this.details = details;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Set<Medication> getMedications() {
		return Medications;
	}

	public void setMedications(Set<Medication> medications) {
		Medications = medications;
	}


	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public void addMedication(Medication MedObj) {
		this.Medications.add(MedObj);
		MedObj.getPrescriptions().add(this);
	}


	public void removeMedication(Medication MedObj) {
		MedObj.getPrescriptions().remove(this);
		this.Medications.remove(MedObj);
	}

}
