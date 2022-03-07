package developers.scasystem.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "appointment")
public class Appointment {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "appointmentid",nullable = false)
	private int AppointmentID;
	
	@Column(name = "appointmentdate",nullable = false)
	private Date AppoitnemtnDate;
	
	@Column(name = "starttime",nullable = false)
	private String StartTime;
	
	@Column(name = "endtime",nullable = false)
	private String EndTime;
	
	@Column(name = "location",nullable = false)
	private String Location;
	
	@Column(name = "roomnumber")
	private String RoomNumber;
	//doctor has many appointment
	//appointment has one doctor 
	//appointment has one patient
	//patient has many appointment
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "staff_ID",nullable = false)
	@JsonIgnore
	private Staff staff;
	
	public Appointment() {
		
	}
	public Appointment(int AppointmentID,Date AppointmentDate ,String StartTime, String EndTime, String Location, String RoomNumber ) {
		this.AppointmentID = AppointmentID;
		this.AppoitnemtnDate = AppoitnemtnDate;
		this.StartTime = StartTime;
		this.EndTime = EndTime;
		this.Location = Location;
		this.RoomNumber = RoomNumber;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAppointmentID() {
		return AppointmentID;
	}
	public void setAppointmentID(int appointmentID) {
		AppointmentID = appointmentID;
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
		return RoomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		RoomNumber = roomNumber;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	
	
}
