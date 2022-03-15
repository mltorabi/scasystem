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
import org.springframework.web.bind.annotation.RestController;

import developers.scasystem.model.Medication;
import developers.scasystem.model.MedicationRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MedicationContoller {
	
	
	
	@Autowired
	MedicationRepository medicationRepo;
	
	
	
	@GetMapping("/medication")
	public ResponseEntity<List<Medication>> getAllMedication() {
		
		
		try {
			
			
			List<Medication> medicationList = new ArrayList<Medication>();
			
			medicationRepo.findAll().forEach(medicationList::add);
			
			return new ResponseEntity<>(medicationList, HttpStatus.OK);
			
			
			
		}catch(Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	
	
	//display medication by id
	@GetMapping("/medication/{id}")
	public ResponseEntity<Medication> getAllMedicationtById(@PathVariable("id") long id){
		
		try {
			
		
			Optional<Medication> medication = medicationRepo.findById(id);
			
			
			if(medication.isPresent()) {
				
				return new ResponseEntity<>(medication.get(), HttpStatus.OK);
				
			}else {
				
				
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}		
			
		}catch(Exception e) {
			
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
	}
	
	
	
	
	//add medication
	@PostMapping("/medication")
	public ResponseEntity<Medication> createPatient(@RequestBody Medication medicationBody){
		
		try {
			
			
				Medication _medication = medicationRepo.save(new Medication(medicationBody.getMedTitle(), medicationBody.getMedUses(), medicationBody.getMedSideEffect(), medicationBody.getMedOverdose()));
			
				return new ResponseEntity<>(_medication , HttpStatus.CREATED); 
			
		}catch(Exception e) {
			
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
	}
	
	@PutMapping("/medication/{id}")
	public ResponseEntity<Medication> editMedication(@PathVariable("id") long id, @RequestBody Medication medicationBody){
		
	
		try {
			
			
			Optional<Medication> _medication = medicationRepo.findById(id);
			
			if(_medication .isPresent()) {
				
				_medication.get().setMedTitle(medicationBody.getMedTitle());
				_medication.get().setMedUses(medicationBody.getMedUses());
				_medication.get().setMedSideEffect(medicationBody.getMedSideEffect());
				_medication.get().setMedUses(medicationBody.getMedUses());
				
				return new ResponseEntity<>(medicationRepo.save(_medication.get()), HttpStatus.OK);
				
				
				
			}else {
				
				
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				
				
			}
			
		
		}catch(Exception e) {
		
		
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		
		
			}
		
	}
	
	
	
	//Delete Medication by id
	@DeleteMapping("/medication/{id}")
	public ResponseEntity<Medication> deleteMedicationbyId(@PathVariable("id") long id){
			try {
				
				
				medicationRepo.deleteById(id);
				
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				
				
			}catch(Exception e) {
				
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
	
	}
	
	//delete all  medication
	@DeleteMapping("/medication")
	public ResponseEntity<Medication> deleteAllMedication(){
			try {
				
				medicationRepo.deleteAll();
				
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				
				
			}catch(Exception e) {
				
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
	
	
	}
	
	
	
	
	
	


}
