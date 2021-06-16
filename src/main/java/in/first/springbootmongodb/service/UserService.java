package in.first.springbootmongodb.service;

import in.first.springbootmongodb.exception.PatientCollectionException;
import in.first.springbootmongodb.model.Patient;

public interface UserService {
		
	public Patient getSingleUser(Patient patient) throws PatientCollectionException;
}
