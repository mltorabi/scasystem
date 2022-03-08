package developers.scasystem.controller;

import java.util.Date;
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

import developers.scasystem.model.Staff;
import developers.scasystem.model.StaffRepository;

@CrossOrigin(origins="http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StaffController {

	@Autowired
	StaffRepository staffRepository;


	@GetMapping("/staff")
	public ResponseEntity<List<Staff>> findAllStaff(){
		try {
			return new ResponseEntity(staffRepository.findAll(),HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/staff/{id}")
	public ResponseEntity<Staff> findStaffById(@PathVariable("id") long id){
		Optional<Staff> staffData=staffRepository.findById(id);
		if (staffData.isPresent()){
			return new ResponseEntity<>(staffData.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/staff")
	public ResponseEntity<Staff> createStaff(@RequestBody Staff staff){

		try {
//			Staff _staff=new Staff(
//					staff.getStaffID(),
//					staff.getFirstName(),
//					staff.getLastName(),
//					staff.getBirthDate(),
//					staff.getPhone(),
//					staff.getEmail(),
//					staff.getUsertype());
				return new ResponseEntity(staffRepository.save(staff),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/staff/{id}")
	public ResponseEntity<Staff> updateStaffById(@PathVariable("id") long id,
	@RequestBody Staff staff){
		Optional<Staff> staffData=staffRepository.findById(id);
		if(staffData.isPresent()) {
			Staff _staff=staffData.get();
			_staff.setStaffID(staff.getStaffID());
			_staff.setFirstName(staff.getFirstName());
			_staff.setLastName(staff.getLastName());
			_staff.setBirthDate(staff.getBirthDate());
			_staff.setPhone(staff.getPhone());
			_staff.setEmail(staff.getEmail());
			_staff.setUsertype(staff.getUsertype());
			return new ResponseEntity(HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}		
	}

	@DeleteMapping("/staff/{id}")
	public ResponseEntity<HttpStatus> deleteStaffById(@PathVariable("id") long id)	{
		try {
			staffRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/staff")
	public ResponseEntity<HttpStatus> deleteAllStaff(){

		try {
			staffRepository.deleteAll();
			return new ResponseEntity(HttpStatus.NO_CONTENT);

		}catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}


}