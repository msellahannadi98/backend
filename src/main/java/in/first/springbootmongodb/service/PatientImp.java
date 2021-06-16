package in.first.springbootmongodb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.first.springbootmongodb.exception.PatientCollectionException;
import in.first.springbootmongodb.model.Patient;
import in.first.springbootmongodb.repository.PatientRepository;

@Service
public class PatientImp implements PatientService{
	@Autowired
	private PatientRepository patientRepo;
	
	@Override
	public void createPatient(Patient patient) throws PatientCollectionException{
		Optional<Patient> patientOptional = patientRepo.findByPatient(patient.getEmail()) ;
		if(patientOptional.isPresent()) {
			throw new PatientCollectionException(PatientCollectionException.PatientAlreadyExist());
		}
		else {
			patient.setCreatedAt(new Date(System.currentTimeMillis()));
			patientRepo.save(patient);
		}
	}
	
	@Override
	public List<Patient> getAllPatient(){
		List<Patient> patientlist=patientRepo.findAll();
		if(patientlist.size()>0) {
			return patientlist;
		}else {
			return new ArrayList<Patient>();
		}
	}
	
	@Override
	public Patient getSinglePatient(String id) throws PatientCollectionException{
		Optional<Patient> optionalPatient=patientRepo.findById(id);
		if(!optionalPatient.isPresent()) {
			throw new PatientCollectionException(PatientCollectionException.NotFoundExeption(id));
		}
		else {
			return  optionalPatient.get();
		}
	}
	
	@Override
	public void updatePatient(String id,Patient patient) throws PatientCollectionException{
		Optional<Patient> patientWithId=patientRepo.findById(id);
		if(patientWithId.isPresent()) {
			Patient patientToUpdate=patientWithId.get();
			
			patientToUpdate.setFirstName(patient.getFirstName());
			patientToUpdate.setLastName(patient.getLastName());
			patientToUpdate.setAddress(patient.getAddress());
			patientToUpdate.setPassword(patient.getPassword());
			patientToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
			
			patientRepo.save(patientToUpdate);		
			
		}
		else {
			throw new PatientCollectionException(PatientCollectionException.NotFoundExeption(id));
		}
	}
}
