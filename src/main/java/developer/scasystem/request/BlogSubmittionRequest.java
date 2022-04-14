package developer.scasystem.request;

import javax.validation.constraints.NotBlank;

public class BlogSubmittionRequest {
	@NotBlank(message = "User should already logged in")
	private int id;
	
	@NotBlank(message = "type of action not defined")
	private String action;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
}
