package customExceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
//+++++++++++++++++++++++++++++++++++
//	 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	private String userId;
	private String typeId;
//+++++++++++++++++++++++++++++++++++
//	  METHODS
//+++++++++++++++++++++++++++++++++++
	public UserNotFoundException(String userId, String typeId) {
		super("the user was not found: ");
		this.userId=userId;
		this.typeId=typeId;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage()+"\n type id: "+typeId+"\n id"+userId;
	}
	
}
