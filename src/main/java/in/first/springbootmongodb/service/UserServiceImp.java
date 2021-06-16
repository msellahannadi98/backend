package in.first.springbootmongodb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.first.springbootmongodb.exception.PatientCollectionException;
import in.first.springbootmongodb.model.Patient;
import in.first.springbootmongodb.repository.PatientRepository;

@Service
public class UserServiceImp  implements UserService{
	
	@Autowired
	private PatientRepository patientRepo;
	
	public Patient getSingleUser(Patient patient) throws PatientCollectionException{
		Optional<Patient> optionalPatient=patientRepo.findByPatient(patient.getEmail());
		if(!optionalPatient.isPresent()) {
			throw new PatientCollectionException(PatientCollectionException.NotFoundExeption(patient.getEmail()));
		}
		else {
			return  optionalPatient.get();
		}
	}
}
