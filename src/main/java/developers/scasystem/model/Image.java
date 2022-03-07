package developers.scasystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "image")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "alt_text")
	private String AltText;
	
	@Column(name = "pic_cat")
	private int PictureCategory;
	
	@Column(name = "pic_addr")
	private String PictureAddress;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "staff_ID",nullable = false)
	@JsonIgnore
	private Staff staff;
	
	public Image() {
		
	}
	
	public Image(String AltText, int PictureCategory, String PictureAddress) {
		this.AltText = AltText;
		this.PictureCategory = PictureCategory;
		this.PictureAddress = PictureAddress;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAltText() {
		return AltText;
	}

	public void setAltText(String altText) {
		AltText = altText;
	}

	public int getPictureCategory() {
		return PictureCategory;
	}

	public void setPictureCategory(int pictureCategory) {
		PictureCategory = pictureCategory;
	}

	public String getPictureAddress() {
		return PictureAddress;
	}

	public void setPictureAddress(String pictureAddress) {
		PictureAddress = pictureAddress;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	
	
	
}
