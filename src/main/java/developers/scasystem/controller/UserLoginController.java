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

import developer.scasystem.request.PatientLoginRequest;
import developers.scasystem.model.PatientRepository;
import developers.scasystem.model.patient;
import developers.scasystem.response.MessageResponse;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserLoginController {
	@Autowired
	PatientRepository PatientRep;
	
	@PostMapping("/ulogin")
	public ResponseEntity<?> uLogin(@Valid @RequestBody PatientLoginRequest patientRequest){
		try {
			Optional<patient> patientData = PatientRep.findByInsuranceNumber(patientRequest.getInsuranceNumber());
			if(patientData.isPresent()) {
				String password = patientData.get().getPassword();
				if(password.equals(patientRequest.getPassword())) {
					return new ResponseEntity<>(patientData.get(),HttpStatus.OK);
				}
				MessageResponse msg = new MessageResponse("Wrong username/Password");
				return new ResponseEntity<>(msg,HttpStatus.FORBIDDEN);
			}
			MessageResponse msg = new MessageResponse("Wrong username/Password");
			return new ResponseEntity<>(msg,HttpStatus.FORBIDDEN);
		}catch(Exception e) {
			MessageResponse msg = new MessageResponse("Server Error");
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
