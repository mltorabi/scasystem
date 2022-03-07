package developers.scasystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import developers.scasystem.model.PatientRepository;
import developers.scasystem.model.patient;


@RestController
@RequestMapping("/api")
public class patientController {
	
	
	
	
	@Autowired
	PatientRepository patientRepo;

	@GetMapping("/patient")
	public ResponseEntity<List<patient>> getAllPatient(){
		
		try {
			
			List<patient> patientList = new ArrayList<patient>();
			
			patientRepo.findAll().forEach(patientList::add);
			
			
			return new ResponseEntity<>(patientList, HttpStatus.OK);
			
			
		}catch(Exception e) {
			
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
	}
	
	@GetMapping("/patient/{id}")
	public ResponseEntity<patient> getAllPatientById(@PathVariable long id){
		
		try {
			
		
			Optional<patient> patient = patientRepo.findById(id);
			
			
			if(patient.isPresent()) {
				
				return new ResponseEntity<>(patient.get(), HttpStatus.OK);
				
			}else {
				
				
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

			
			
		}catch(Exception e) {
			
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
	}
	
	
	
	@PostMapping("/patient")
	public ResponseEntity<patient> createPatient(@RequestBody patient patient){
		
		try {
			
			patient _patient = new patient(
					patient.getInsuranceNumber(),
					patient.getFirstName(),
					patient.getLastName(),
					patient.getBirthDate(),
					patient.getPhone(),
					patient.getEmail(),
					patient.getStreetAddress(),
					patient.getProvince(),
					patient.getPostalCode(),
					patient.getCity(),
					patient.getPassword()
					);
			
			return new ResponseEntity<>(patientRepo.save(_patient), HttpStatus.OK);
			
			
			
		}catch(Exception e) {
			
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
	}
	
	
	@PutMapping("/patient/{id}")
	public ResponseEntity<patient> editPatient(@PathVariable long id, patient patient){
		
		try {
			
			
			Optional<patient> _patient  = patientRepo.findById(id);
			
			
			if(_patient.isPresent()) {
		
			_patient.get().setInsuranceNumber(patient.getInsuranceNumber());
			_patient.get().setFirstName(patient.getFirstName());
			_patient.get().setLastName(patient.getLastName());
			_patient.get().setBirthDate(patient.getBirthDate());
			_patient.get().setPhone(patient.getPhone());
			_patient.get().setEmail(patient.getEmail());
			_patient.get().setStreetAddress(patient.getStreetAddress());
			_patient.get().setProvince(patient.getProvince());
			_patient.get().setPostalCode(patient.getPostalCode());
			_patient.get().setCity(patient.getCity());
			_patient.get().setPassword(patient.getPassword());
			
			
			return new ResponseEntity<>(patientRepo.save(_patient.get()), HttpStatus.OK);
			
			
			}
			else {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				
			}
	
			
		}catch(Exception e) {
			
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
	}
	
	
	@DeleteMapping("/patient/{id}")
	public ResponseEntity<patient> deletePatientbyId(@PathVariable long id){
			try {
				
				
				patientRepo.deleteById(id);
				
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				
				
			}catch(Exception e) {
				
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
	
	
	}
	
	
	
	@DeleteMapping("/patient")
	public ResponseEntity<patient> deleteAllPatient(){
			try {
				
				
				patientRepo.deleteAll();
				
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				
				
			}catch(Exception e) {
				
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
	
	
	}
	
	
	
	
	
}
