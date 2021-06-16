package in.first.springbootmongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.first.springbootmongodb.exception.PatientCollectionException;
import in.first.springbootmongodb.model.Patient;
import in.first.springbootmongodb.service.PatientService;
import in.first.springbootmongodb.service.UserService;

@RestController
@RequestMapping(value = "/wecare")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private UserService userService;

	
	@PostMapping("/user")
	public ResponseEntity<?> getuserDetails(@RequestBody Patient patient) {
		try {
			return new ResponseEntity<>(userService.getSingleUser(patient), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/patient")
	public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
		try {
			patientService.createPatient(patient);
			return new ResponseEntity<Patient>(patient, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/patient")
	public ResponseEntity<?> getAllPatient() {
		List<Patient> patientlist = patientService.getAllPatient();
		return new ResponseEntity<List<Patient>>(patientlist,
				patientlist.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@GetMapping("/patient/{id}")
	public ResponseEntity<?> getSinglePatient(@PathVariable String id) {
		try {
			return new ResponseEntity<>(patientService.getSinglePatient(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/patient/{id}")
	public ResponseEntity<?> updatePatient(@PathVariable String id, @RequestBody Patient patient) {
		try {
			patientService.updatePatient(id, patient);
			return new ResponseEntity<>("Updated with id " + id, HttpStatus.OK);
		} catch (PatientCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);

		}
	}
}
