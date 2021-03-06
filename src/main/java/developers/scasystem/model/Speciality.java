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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Speciality")
public class Speciality {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToMany(mappedBy = "Speciality", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	private Set<Staff> staff = new HashSet<>();
	
	@Column(name = "speciality")
	private String speciality;
	
	@Column(name = "Experience")
	private int Experience;
	
	public void AddStaff(Staff staff) {
		this.staff.add(staff);
		staff.getSpeciality().add(this);
	}
	
	public void RemoveStaff(Staff staff) {
		staff.getSpeciality().remove(this);
		this.staff.remove(staff);
	}

	public Speciality() {
		
	}
	public Speciality(String specialityTitle, int experience) {
		this.speciality = specialityTitle;
		this.Experience = experience;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Staff> getStaff() {
		return staff;
	}

	public void setStaff(Set<Staff> staff) {
		this.staff = staff;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public int getExperience() {
		return Experience;
	}

	public void setExperience(int experience) {
		Experience = experience;
	}
}
