package in.first.springbootmongodb.service;

import java.util.List;

import in.first.springbootmongodb.exception.PatientCollectionException;
import in.first.springbootmongodb.model.Patient;

public interface PatientService {
	
	public void createPatient(Patient patient) throws PatientCollectionException;
	
	public List<Patient> getAllPatient();
	
	public Patient getSinglePatient(String id) throws PatientCollectionException;
	
	public void updatePatient(String id, Patient patient) throws PatientCollectionException;
}
