package customExceptions;

@SuppressWarnings("serial")
public class UserAlreadyExistException extends Exception {
//+++++++++++++++++++++++++++++++++++
//	 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	private String userId;
	private String userName;
	private String typeId;
//+++++++++++++++++++++++++++++++++++
//	  METHODS
//+++++++++++++++++++++++++++++++++++
	public UserAlreadyExistException(String userId, String userName,String typeId) {
		super("the already Exist: ");
		this.userId=userId;
		this.userName=userName;
		this.typeId=typeId;
	}
	
	/**
	 * <b>Name:</b> getMessage.<br>
	 * This method return the message of the Exception with its attributes.<br>
	 * @return the message of the Exception with its attributes.<br>
	*/
	@Override
	public String getMessage() {
		return super.getMessage()+"\n Name: "+userName+"\n type id: "+typeId+"\n id: "+userId;
	}
}
