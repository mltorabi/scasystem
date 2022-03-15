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



import developers.scasystem.model.Appointment;
import developers.scasystem.model.AppointmentRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AppointmentController {
	@Autowired
	AppointmentRepository appRep;
	
	@GetMapping("/appointment/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") long id) {
		Optional<Appointment> appData = appRep.findById(id);

		if (appData.isPresent()) {
			return new ResponseEntity<>(appData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	@GetMapping("/appointment")
	public ResponseEntity<List<Appointment>> getAllAppointment(@RequestParam(required = false) String roomnumber) {

		try {
			List<Appointment> appointments = new ArrayList<Appointment>();
			if (roomnumber == null) {
				appRep.findAll().forEach(appointments::add);
			} else {
				appRep.findByRoomNumber(roomnumber).forEach(appointments::add);
			}
			return new ResponseEntity<>(appointments, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping("/appointment")
	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointmentBody) {
		try {
			Appointment _Appointment = appRep.save(new Appointment(appointmentBody.getAppoitnemtnDate(),appointmentBody.getStartTime(),appointmentBody.getEndTime(),appointmentBody.getLocation(),appointmentBody.getRoomNumber()));
			return new ResponseEntity<>(_Appointment, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/appointment/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable("id") long id, @RequestBody Appointment appBody) {
		Optional<Appointment> AppointmentData = appRep.findById(id);

		if (AppointmentData.isPresent()) {
			Appointment _Appointment = AppointmentData.get();
			_Appointment.setAppoitnemtnDate(appBody.getAppoitnemtnDate());
			_Appointment.setStartTime(appBody.getStartTime());
			_Appointment.setEndTime(appBody.getEndTime());
			_Appointment.setLocation(appBody.getLocation());
			_Appointment.setRoomNumber(appBody.getRoomNumber());
			return new ResponseEntity<>(appRep.save(_Appointment), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/appointment/{id}")
	public ResponseEntity<HttpStatus> deleteAppointment(@PathVariable("id") long id) {
		try {
			appRep.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/appointment")
	public ResponseEntity<HttpStatus> deleteAllAppointment() {
		try {
			appRep.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
