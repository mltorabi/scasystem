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

import developers.scasystem.model.Speciality;
import developers.scasystem.model.SpecialityRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SpecialityController {
	@Autowired
	SpecialityRepository specialityRep;
	
	@GetMapping("/speciality")
	public ResponseEntity<List<Speciality>> showAllSpeciality(@RequestParam(required = false)String speciality){
		try {
			List<Speciality> specialities = new ArrayList<Speciality>();
			if(speciality  == null) {
				specialityRep.findAll().forEach(specialities::add);
			}else {
				specialityRep.findByspeciality(speciality).forEach(specialities::add);
			}
			return new ResponseEntity<>(specialities,HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/specility/{id}")
	public ResponseEntity<Speciality> getSpecilaityById(@PathVariable("id")long id){
		Optional<Speciality> specialityData = specialityRep.findById(id);
		if(specialityData.isPresent()) {
			return new ResponseEntity<>(specialityData.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/speciality")
	public ResponseEntity<Speciality> addSpeciality(@RequestBody Speciality specilaityBody){
		try {
			Speciality _speciality = specialityRep.save(new Speciality(specilaityBody.getSpeciality(), specilaityBody.getExperience()));
			return new ResponseEntity<>(_speciality,HttpStatus.CREATED);
		}catch(Exception ex){
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/speciality/{id}")
	public ResponseEntity<Speciality> UpdateSpeciality(@PathVariable("id")long id,@RequestBody Speciality specialityUpdatebd){
		Optional<Speciality> specialityData = specialityRep.findById(id);
		if(specialityData.isPresent()) {
			Speciality _speciality = specialityData.get();
			_speciality.setExperience(specialityUpdatebd.getExperience());
			_speciality.setSpeciality(specialityUpdatebd.getSpeciality());
			return new ResponseEntity<>(specialityRep.save(_speciality),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/speciality/{id}")
	public ResponseEntity<HttpStatus> deleteSpecialityById(@PathVariable("id")long id){
		try {
			specialityRep.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/speciality")
	public ResponseEntity<HttpStatus> deleteAllSpeciality(){
		try {
			specialityRep.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
