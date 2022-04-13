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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "insuranceNumber",nullable = false)
	private int insuranceNumber;
	
	@Column(name = "firstname",nullable = false)
	private String FirstName;
	
	@Column(name = "lastname",nullable = false)
	private String LastName;
	
	@Column(name = "birthdate")
	private String BirthDate;
	
	@Column(name = "Phone",nullable = false)
	private String Phone;
	
	@Column(name = "email")
	private String Email;
	
	@Column(name = "password")
	private String Password;
	
	@Column(name = "streetaddr")
	private String StreetAddress;
	
	@Column(name = "province")
	private String Province;
	
	@Column(name = "postalcode",length = 7)
	private String PostalCode;
	
	@Column(name = "city")
	private String City;
	
	@OneToMany(mappedBy = "patient",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private Set<Appointment> appointments = new HashSet<>();
	
	public void AddAppointment(Appointment appointment) {
		this.appointments.add(appointment);
		appointment.setPatient(this);
	}
	
	public void RemoveAppointment(Appointment appointment) {
		this.appointments.remove(appointment);
	}
	
	public Set<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
	public patient() {
		
	}
	public patient(int InsuranceNumber,String FirstName, String LastName, String BirthDate, String Phone, String Email, String StreetAddress, String Province, String PostalCode, String City, String Password) {
		this.insuranceNumber = InsuranceNumber;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.BirthDate = BirthDate;
		this.Phone = Phone;
		this.Email = Email;
		this.StreetAddress = StreetAddress;
		this.Province = Province;
		this.PostalCode = PostalCode;
		this.City = City;
		this.Password = Password;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(int insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
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

	public String getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(String birthDate) {
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

	public String getStreetAddress() {
		return StreetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		StreetAddress = streetAddress;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getPostalCode() {
		return PostalCode;
	}

	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}
	
	
}
