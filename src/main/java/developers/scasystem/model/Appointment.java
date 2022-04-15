package developers.scasystem.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "appointment")
public class Appointment {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "appointmentdate")
	private String AppoitnemtnDate;
	
	@Column(name = "starttime")
	private String StartTime;
	
	@Column(name = "endtime")
	private String EndTime;
	
	@Column(name = "location")
	private String Location;
	
	@Column(name = "roomnumber")
	private String roomnumber;
	//doctor has many appointment
	//appointment has one doctor 
	//appointment has one patient
	//patient has many appointment
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Staff> Staff = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<patient> patient = new HashSet<>();
	
	//an appointment may have one prescription
	//the owner of the FK should take the JoinColumn
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prescription_id",referencedColumnName = "id")
	@JsonIgnore
	private Prescription prescription;
	
	@Column(name = "status")
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Appointment() {
		
	}
	public Appointment(String AppointmentDate ,String StartTime, String EndTime, String Location, String RoomNumber ) {
		this.AppoitnemtnDate = AppointmentDate;
		this.StartTime = StartTime;
		this.EndTime = EndTime;
		this.Location = Location;
		this.roomnumber = RoomNumber;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAppoitnemtnDate() {
		return AppoitnemtnDate;
	}
	public void setAppoitnemtnDate(String appoitnemtnDate) {
		AppoitnemtnDate = appoitnemtnDate;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public Prescription getPrescription() {
		return prescription;
	}
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getRoomNumber() {
		return roomnumber;
	}
	public void setRoomNumber(String roomNumber) {
		roomnumber = roomNumber;
	}
	public String getRoomnumber() {
		return roomnumber;
	}
	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
	}
	public Set<Staff> getStaff() {
		return Staff;
	}
	public void setStaff(Set<Staff> staff) {
		Staff = staff;
	}
	public Set<patient> getPatient() {
		return patient;
	}
	public void setPatient(Set<patient> patient) {
		this.patient = patient;
	}
	
	public void AddStaff(Staff staff) {
		this.Staff.add(staff);
		staff.getAppointments().add(this);
	}
	public void RemoveStaff(Staff staff) {
		this.Staff.remove(staff);
		staff.getAppointments().remove(this);
	}
	
	public void AddPatient(patient patient) {
		this.patient.add(patient);
		patient.getAppointments().add(this);
	}
	public void RemovePatient(patient patient) {
		this.patient.remove(patient);
		patient.getAppointments().remove(this);
	}
	
	
}
