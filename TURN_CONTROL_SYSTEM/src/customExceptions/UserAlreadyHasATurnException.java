package customExceptions;

@SuppressWarnings("serial")
public class UserAlreadyHasATurnException extends Exception {
//+++++++++++++++++++++++++++++++++++
//	 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	private String userId;
	private String userName;
	private String turnNumber;
//+++++++++++++++++++++++++++++++++++
//	  METHODS
//+++++++++++++++++++++++++++++++++++
	public UserAlreadyHasATurnException(String userId, String userName, String turnNumber) {
		super("The user already has an active turn: ");
		this.userId = userId;
		this.userName = userName;
		this.turnNumber = turnNumber;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage()+"\n Name:"+userName+"\n id"+userId+"\n turn number"+turnNumber;
	}
	
}
