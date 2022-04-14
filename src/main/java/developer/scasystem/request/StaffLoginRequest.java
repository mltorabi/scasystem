package developer.scasystem.request;

import javax.validation.constraints.NotBlank;

public class StaffLoginRequest {
	@NotBlank(message = "Email should be filled")
	private String email;
	
	@NotBlank(message = "password should be filled")
	private String Password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	
}
