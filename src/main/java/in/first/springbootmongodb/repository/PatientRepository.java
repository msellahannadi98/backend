package in.first.springbootmongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import in.first.springbootmongodb.model.Patient;


@Repository
public interface PatientRepository extends MongoRepository<Patient, String>{
	 
	@Query("{'email':?0}")
	Optional<Patient> findByPatient(String email);

}
