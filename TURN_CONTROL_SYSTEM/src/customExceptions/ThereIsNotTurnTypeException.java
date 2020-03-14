package customExceptions;

@SuppressWarnings("serial")
public class ThereIsNotTurnTypeException extends Exception{

	public ThereIsNotTurnTypeException(){
		super("there are no turnType in the program to create turns");
	}
}
