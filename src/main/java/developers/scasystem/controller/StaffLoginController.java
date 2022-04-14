package developers.scasystem.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import developer.scasystem.request.StaffLoginRequest;
import developers.scasystem.model.Staff;
import developers.scasystem.model.StaffRepository;
import developers.scasystem.response.MessageResponse;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StaffLoginController {
	@Autowired
	StaffRepository staffRep;
	
	@PostMapping("/slogin")
	public ResponseEntity<?> slogin(@Valid @RequestBody StaffLoginRequest staffRequest){
		try {
			Optional<Staff> staffData = staffRep.findByEmail(staffRequest.getEmail());
			if(staffData.isPresent()) {
				String password = staffData.get().getPassword();
				if(password.equals(staffRequest.getPassword())) {
					return new ResponseEntity<>(staffData,HttpStatus.OK);
				}
				MessageResponse msg = new MessageResponse("worng username/password");
				return new ResponseEntity<>(msg,HttpStatus.FORBIDDEN);
			}
			MessageResponse msg = new MessageResponse("Wrong username/password");
			return new ResponseEntity<>(msg,HttpStatus.FORBIDDEN);
		}catch(Exception e) {
			MessageResponse msg = new MessageResponse("Server Error");
			return new ResponseEntity<>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
