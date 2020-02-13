package customExceptions;

@SuppressWarnings("serial")
public class ThereAreNoTurnsForAttendException extends Exception{

	public ThereAreNoTurnsForAttendException() {
		super("there are no turns for attend at this moment");
	}
}
