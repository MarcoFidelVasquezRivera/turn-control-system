package customExceptions;

@SuppressWarnings("serial")
public class TurnTypeAlreadyExistException extends Exception {
private String name;
	
	public TurnTypeAlreadyExistException(String n) {
		super("The turnType alreasy exist: ");
		name=n;
	}
	
	public String toString() {
		return super.getMessage()+"\n Name"+name;
	}
}
