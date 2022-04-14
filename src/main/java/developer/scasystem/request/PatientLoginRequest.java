package developer.scasystem.request;

import javax.validation.constraints.NotBlank;

public class PatientLoginRequest {
	@NotBlank(message = "Insurance Number should be filled")
	private int insuranceNumber;
	
	@NotBlank(message = "Password should be filled")
	private String Password;

	public int getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(int insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	

}
