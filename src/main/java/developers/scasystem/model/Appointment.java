package developers.scasystem.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	@Column(name = "appointmentdate",nullable = false)
	private Date AppoitnemtnDate;
	
	@Column(name = "starttime",nullable = false)
	private String StartTime;
	
	@Column(name = "endtime",nullable = false)
	private String EndTime;
	
	@Column(name = "location",nullable = false)
	private String Location;
	
	@Column(name = "roomnumber")
	private String roomnumber;
	//doctor has many appointment
	//appointment has one doctor 
	//appointment has one patient
	//patient has many appointment
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "staff_ID",nullable = false)
	@JsonIgnore
	private Staff Staff;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "patient_ID",nullable = false)
	@JsonIgnore
	private patient patient;
	
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
	public patient getPatient() {
		return patient;
	}
	public void setPatient(patient patient) {
		patient = patient;
	}
	public Appointment() {
		
	}
	public Appointment(Date AppointmentDate ,String StartTime, String EndTime, String Location, String RoomNumber ) {
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
	public Date getAppoitnemtnDate() {
		return AppoitnemtnDate;
	}
	public void setAppoitnemtnDate(Date appoitnemtnDate) {
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
	public Staff getStaff() {
		return Staff;
	}
	public void setStaff(Staff staff) {
		this.Staff = staff;
	}
	
	
	
}
