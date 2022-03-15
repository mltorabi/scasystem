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

import developers.scasystem.model.Prescription;
import developers.scasystem.model.PrescriptionRepositry;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PrescriptionController {

	
	@Autowired
	PrescriptionRepositry prescriptionRepo;
	
	
	
	//get all prescription
	@GetMapping("/prescription")
	public ResponseEntity<List<Prescription>> getAllPrescription (){
		

		try {
			
			
			List<Prescription> prescriptionList = new ArrayList<Prescription>();
			
			prescriptionRepo.findAll().forEach(prescriptionList::add);
			
			
			return new ResponseEntity<>(prescriptionList, HttpStatus.OK);
			
		}catch(Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	//get all prescription by id
	@GetMapping("/prescription/{id}")
	public ResponseEntity<Prescription> getAllPrescriptionById(@PathVariable("id") long id){
		
		try {
			
		
			Optional<Prescription> prescription = prescriptionRepo.findById(id);
			
			
			if(prescription.isPresent()) {
				
				return new ResponseEntity<>(prescription.get(), HttpStatus.OK);
				
			}else {
				
				
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}		
			
		}catch(Exception e) {
			
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		
	}
	
	
	
	//creating prescription
	@PostMapping("/prescription")
	public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription prescriptionBody){
		
		try {
		
			Prescription _prescription = prescriptionRepo.save(new Prescription(prescriptionBody.getAppointment(), prescriptionBody.getDetails()));
			
			return new ResponseEntity<>(_prescription , HttpStatus.CREATED); 
		
			}catch(Exception e) {
	
					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	
	
	//edit prescription
	@PutMapping("/prescription/{id}")
	public ResponseEntity<Prescription> editPrescription(@PathVariable("id") long id, @RequestBody Prescription prescriptionBody){
		
		try {
			
			Optional<Prescription> _prescription = prescriptionRepo.findById(id);
			
			if(_prescription.isPresent()) {
				
				_prescription.get().setAppointment(prescriptionBody.getAppointment());
				_prescription.get().setDetails(prescriptionBody.getDetails());
				
				return new ResponseEntity<>(prescriptionRepo.save(_prescription.get()), HttpStatus.OK);
				

			}else {
				

				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				
			}

			
		}catch(Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

	}
	

	
	//delete prescription by id
	
	@DeleteMapping("/prescription/{id}")
	public ResponseEntity<Prescription> deletePrescriptionById(@PathVariable("id") long id){
		
		try {
			
			
			prescriptionRepo.deleteById(id);

			
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
			
			
		}
		catch(Exception e) {
			
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
		
	
	//delete all prescription
	
	@DeleteMapping("/prescription")
	public ResponseEntity<Prescription> deletePrescriptionById(){
		
		try {
			
			
			prescriptionRepo.deleteAll();
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	
			
		}
		catch(Exception e) {
			
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
		
		
	
	
	
	
	
	
	
	
}
