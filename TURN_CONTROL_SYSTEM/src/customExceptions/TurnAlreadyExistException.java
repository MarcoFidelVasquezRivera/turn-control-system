package customExceptions;

@SuppressWarnings("serial")
public class TurnAlreadyExistException extends Exception{

	private String name;
	
	public TurnAlreadyExistException(String n) {
		super("The turn alreasy exist: ");
		name=n;
	}
	
	public String toString() {
		return super.getMessage()+"\n Name"+name;
	}
}
