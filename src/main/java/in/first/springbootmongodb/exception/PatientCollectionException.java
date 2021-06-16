package in.first.springbootmongodb.exception;

public class PatientCollectionException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PatientCollectionException(String message) {
		super(message);
	}
	public static String NotFoundExeption(String id) {
		return "Patient with "+id +" is not found";
	}
	public static String PatientAlreadyExist() {
		return "email is alreay existing";
	}

}
