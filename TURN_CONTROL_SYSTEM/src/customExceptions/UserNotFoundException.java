package customExceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
//+++++++++++++++++++++++++++++++++++
//	 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	private String userId;
//+++++++++++++++++++++++++++++++++++
//	  METHODS
//+++++++++++++++++++++++++++++++++++
	public UserNotFoundException(String userId) {
		super("the user was not found: ");
		this.userId=userId;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage()+"\n id"+userId;
	}
	
}
