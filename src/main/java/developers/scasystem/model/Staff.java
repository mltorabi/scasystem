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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "staff")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "staffID",nullable = false)
	private int StaffID;
	
	@Column(name = "firstname",nullable = false)
	private String FirstName;
	
	@Column(name = "lastname",nullable = false)
	private String LastName;
	
	@Column(name = "birthdate")
	private Date BirthDate;
	
	@Column(name = "phone",nullable = false)
	private String Phone;
	
	@Column(name = "email")
	private String Email;
	
	@Column(name = "passsword",nullable = false)
	private String Password;
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}

	@Column(name = "usertype")
	private int usertype;
	//a doctor may have many specialty
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "speciality_transaction", joinColumns = {
			@JoinColumn(name = "staffID", referencedColumnName = "id")},inverseJoinColumns = {
					@JoinColumn(name = "specialityID",referencedColumnName = "id")})
	private Set<Speciality> Speciality = new HashSet<>();
	
	//a doctor may have many appointment
	@OneToMany(mappedBy = "Staff", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private Set<Appointment> appointments = new HashSet<>();
	public Staff() {
		
	}
	public Set<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
	public Staff(int StaffID, String FirstName, String LastName, Date BirthDate, String Phone, String Email, int UserType) {
		this.StaffID = StaffID;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.BirthDate = BirthDate;
		this.Phone = Phone;
		this.Email = Email;
		this.usertype = UserType;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStaffID() {
		return StaffID;
	}

	public void setStaffID(int staffID) {
		StaffID = staffID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public Date getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(Date birthDate) {
		BirthDate = birthDate;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
	public Set<Speciality> getSpeciality() {
		return Speciality;
	}
	public void setSpeciality(Set<Speciality> speciality) {
		Speciality = speciality;
	}
	
	public void AddAppointment(Appointment appointment) {
		this.appointments.add(appointment);
		appointment.setStaff(this);
	}	
	
	public void RemoveAppointment(Appointment appointment) {
		this.appointments.remove(appointment);
	}
}