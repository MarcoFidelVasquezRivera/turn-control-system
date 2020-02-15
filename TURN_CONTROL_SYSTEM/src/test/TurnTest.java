package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Turn;

class TurnTest {
	Turn turn;
	
	
	public void setup1() {
		turn = new Turn("A00", "Marco Fidel Vasquez Rivera", "1006309297", Turn.NOT_ATTENDED_YET);
	}
	
	@Test
	public void testUser() {
		setup1();
		assertTrue("turn constructor is not working corrently to assign the number",turn.getNumber().equalsIgnoreCase("A00"));
		assertTrue("turn constructor is not working corrently to assign the user name",turn.getUserName().equalsIgnoreCase("Marco Fidel Vasquez Rivera"));
		assertTrue("turn constructor is not working corrently to assign the user ID",turn.getUserId().equalsIgnoreCase("1006309297"));
		assertTrue("turn constructor is not working corrently to assign the user status",turn.getUserStatus().equalsIgnoreCase(Turn.NOT_ATTENDED_YET));
	}

}
