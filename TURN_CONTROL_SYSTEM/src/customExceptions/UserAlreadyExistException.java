package customExceptions;

@SuppressWarnings("serial")
public class UserAlreadyExistException extends Exception {
//+++++++++++++++++++++++++++++++++++
//	 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	private String userId;
	private String userName;
//+++++++++++++++++++++++++++++++++++
//	  METHODS
//+++++++++++++++++++++++++++++++++++
	public UserAlreadyExistException(String userId, String userName) {
		super("the already Exist: ");
		this.userId=userId;
		this.userName=userName;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage()+"\n Name:"+userName+"\n id"+userId;
	}
}
