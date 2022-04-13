package developers.scasystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import developers.scasystem.model.PatientRepository;
import developers.scasystem.model.patient;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class patientController {
	
	
	
	
	@Autowired
	PatientRepository patientRepo;

	
	//display all patient
	@GetMapping("/patient")
	public ResponseEntity<List<patient>> getAllPatient() {
		
		try {
			
			List<patient> patientList = new ArrayList<patient>();
			patientRepo.findAll().forEach(patientList::add);
		
			return new ResponseEntity<>(patientList, HttpStatus.OK);
			
		}catch(Exception e) {
			
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
	}
	
	
	//display patient by id
	@GetMapping("/patient/{id}")
	public ResponseEntity<patient> getAllPatientById(@PathVariable("id") long id){
		
		try {
			
		
			Optional<patient> patient = patientRepo.findById(id);
			
			
			if(patient.isPresent()) {
				
				return new ResponseEntity<>(patient.get(), HttpStatus.OK);
				
			}else {
				
				
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}		
			
		}catch(Exception e) {
			
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
	}
	
	
	
	//add patient
	@PostMapping("/patient")
	public ResponseEntity<patient> createPatient(@RequestBody patient patientBody){
		
		try {
			patient _patient = patientRepo.save(new patient(patientBody.getInsuranceNumber(), patientBody.getFirstName(), patientBody.getLastName(), patientBody.getBirthDate(), patientBody.getPhone(), patientBody.getEmail(), patientBody.getStreetAddress(), patientBody.getProvince(), patientBody.getPostalCode(), patientBody.getCity(), patientBody.getPassword()));
			
			return new ResponseEntity<>(_patient, HttpStatus.CREATED);
			
		}catch(Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
	}
	
	
	
	//edit patient
	@PutMapping("/patient/{id}")
	public ResponseEntity<patient> editPatient(@PathVariable("id") long id,@RequestBody patient patient){
		
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
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				
			}
	
		}catch(Exception e) {
			
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
	}
	
	
	//delete patient by id
	@DeleteMapping("/patient/{id}")
	public ResponseEntity<patient> deletePatientbyId(@PathVariable("id") long id){
			try {
				
				
				patientRepo.deleteById(id);
				
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				
				
			}catch(Exception e) {
				
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
	
	}
	
	
	//delete all patient
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
