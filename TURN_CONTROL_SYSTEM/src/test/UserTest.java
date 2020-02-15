package test;

import static org.junit.Assert.*;

import model.Turn;
import model.User;

import org.junit.Test;

public class UserTest {
	private User user;
	private Turn turn;
	
	public void setup1() {
		user = new User(User.CC, "1006309297", "Marco Fidel", "Vasquez Rivera", "333-444", "3163886825");
	}
	
	public void setup2() {
		turn = new Turn("A00", "Marco Fidel Vasquez Rivera", "1006309297", Turn.NOT_ATTENDED_YET);
	}
	
	
	@Test
	public void testUser() {
		setup1();
		setup2();
		assertTrue("Constructor is no working corretly assigning the type of ID",user.getTypeId().equalsIgnoreCase(User.CC));
		assertTrue("Constructor is no working corretly assigning the ID",user.getId().equalsIgnoreCase("1006309297"));
		assertTrue("Constructor is no working corretly assigning the first names",user.getFirstNames().equalsIgnoreCase("Marco Fidel"));
		assertTrue("Constructor is no working corretly assigning the last names",user.getLastNames().equalsIgnoreCase("Vasquez Rivera"));
		assertTrue("Constructor is no working corretly assigning the address",user.getAddress().equalsIgnoreCase("333-444"));
		assertTrue("Constructor is no working corretly assigning the telephone",user.getTelephone().equalsIgnoreCase("3163886825"));
		user.setTurn(turn);
		
		assertTrue("set Turn is not working corrently",user.getTurn().getNumber().equalsIgnoreCase("A00"));
		assertTrue("set Turn is not working corrently",user.getTurn().getUserName().equalsIgnoreCase("Marco Fidel Vasquez Rivera"));
		assertTrue("set Turn is not working corrently",user.getTurn().getUserId().equalsIgnoreCase("1006309297"));
		assertTrue("set Turn is not working corrently",user.getTurn().getUserStatus().equalsIgnoreCase(Turn.NOT_ATTENDED_YET));
		
		user.setTurn(null);
		
		user.setTurn("A00", "Marco Fidel Vasquez Rivera", "1006309297", Turn.NOT_ATTENDED_YET);
		assertTrue("set Turn is not working corrently",user.getTurn().getNumber().equalsIgnoreCase("A00"));
		assertTrue("set Turn is not working corrently",user.getTurn().getUserName().equalsIgnoreCase("Marco Fidel Vasquez Rivera"));
		assertTrue("set Turn is not working corrently",user.getTurn().getUserId().equalsIgnoreCase("1006309297"));
		assertTrue("set Turn is not working corrently",user.getTurn().getUserStatus().equalsIgnoreCase(Turn.NOT_ATTENDED_YET));
		
	}
	
	

}
