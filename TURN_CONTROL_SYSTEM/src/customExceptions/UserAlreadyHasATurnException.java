package customExceptions;

@SuppressWarnings("serial")
public class UserAlreadyHasATurnException extends Exception {
//+++++++++++++++++++++++++++++++++++
//	 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	private String userId;
	private String userName;
	private String turnNumber;
	private String typeId;
//+++++++++++++++++++++++++++++++++++
//	  METHODS
//+++++++++++++++++++++++++++++++++++
	public UserAlreadyHasATurnException(String userId, String typeId, String userName, String turnNumber) {
		super("The user already has an active turn: ");
		this.userId = userId;
		this.userName = userName;
		this.turnNumber = turnNumber;
		this.typeId=typeId;
	}
	
	/**
	 * <b>Name:</b> getMessage.<br>
	 * This method return the message of the Exception with its attributes.<br>
	 * @return the message of the Exception with its attributes.<br>
	*/
	@Override
	public String getMessage() {
		return super.getMessage()+"\n Name:"+userName+"\n type Id: "+typeId+"\n id: "+userId+"\n turn number: "+turnNumber;
	}
	
}
