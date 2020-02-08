package customExceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
//+++++++++++++++++++++++++++++++++++
//	 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	private String userId;
	private String userName;
//+++++++++++++++++++++++++++++++++++
//	  METHODS
//+++++++++++++++++++++++++++++++++++
	public UserNotFoundException(String userId, String userName) {
		super("the user was not found: ");
		this.userId=userId;
		this.userName=userName;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage()+"\n Name:"+userName+"\n id"+userId;
	}
	
}
