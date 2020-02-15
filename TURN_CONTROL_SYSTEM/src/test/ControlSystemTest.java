package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import model.ControlSystem;
import model.Turn;
import model.User;
import customExceptions.*;

class ControlSystemTest {
	ControlSystem cs;
	
	public void setup1() {
		cs = new ControlSystem();
	}
	
	@Test
	public void passTurnTest() {
		setup1();
		assertTrue("number is not initiating in the correct values",cs.getNumber().equalsIgnoreCase("A00"));
		
		for(int i=0;i<=3000;i++) {
			if(i==1) {
				assertTrue("passTurn method is not working correctly",cs.getNumber().equalsIgnoreCase("A01"));
			}else if(i==10){
				assertTrue("passTurn method is not working correctly",cs.getNumber().equalsIgnoreCase("A10"));
			}else if(i==100){
				assertTrue("passTurn method is not working correctly to change the letter",cs.getNumber().equalsIgnoreCase("B00"));
			}else if(i==400){
				assertTrue("passTurn method is not working correctly",cs.getNumber().equalsIgnoreCase("E00"));
			}else if(i==2600){
				assertTrue("passTurn method is not working correctly to change the letter from Z to A",cs.getNumber().equalsIgnoreCase("A00"));
			}
			cs.passTurn();
		}
	}
	
	@Test
	public void resetTurnsTest() {
		setup1();
		cs.passTurn();
		cs.resetTurns();
		assertTrue("resetTurns method is not working corretly",cs.getNumber().equalsIgnoreCase("A00"));

	}
	
	@Test
	public void addNewUserTest() {
		setup1();
		assertTrue("Control System constructor is not working correctly,is creating the object whit users",cs.getUsers().isEmpty());
		try {
			cs.addNewUser(User.CC, "1006309297", "Marco Fidel", "Vasquez Rivera", "333-444", "3163886825");
		}catch(UserAlreadyExistException e) {
			fail("method addNewUser is trowing an exception when it shouldn't, because the user has not been entered yet");
		}
		assertTrue("Control System constructor is not working correctly,is not creating a user when it should",!(cs.getUsers().isEmpty()));
		
		try {
			cs.addNewUser(User.CC, "1006309297", "Marco Fidel", "Vasquez Rivera", "333-444", "3163886825");
			fail("the method add new user is adding a user that already exist");
		}catch(UserAlreadyExistException e) {
			
		}
		
		
	}
	
	@Test
	public void searchUserTest() {
		setup1();
		assertTrue("Control System constructor is not working correctly,is creating the object whit users",cs.getUsers().isEmpty());
		try {
			cs.addNewUser(User.CC, "1006309297", "Marco Fidel", "Vasquez Rivera", "333-444", "3163886825");
			cs.searchUser("1006309297", User.CC);
		}catch(UserNotFoundException e) {
			fail("method searchUser is trowing an exception when it shouldn't, because the user already exist so it should be found");
		}catch(UserAlreadyExistException e) {
			
		}
		
		try {
			cs.searchUser("1006358934", User.CC);
			fail("the metod is finding an user when it does not exist");
		}catch(UserNotFoundException e) {
			
		}	
	}
	
	@Test
	public void assignTurnTest() {
		setup1();
		try {
			cs.addNewUser(User.CC, "1006309297", "Marco Fidel", "Vasquez Rivera", "333-444", "3163886825");
			cs.addNewUser(User.CC, "1006345256", "David Steven", "Montoya Parra", "666-888", "3164574563");
			cs.addNewUser(User.CC, "1006368467", "Alejandro", "Fonseca Forero", "111-000", "3246853460");
			cs.assignTurn("1006345256", User.CC);
		}catch(UserAlreadyExistException e) {
			fail("assignTurn method is not working correctly, is throwing an exception when it shouldn't");
		}catch(UserAlreadyHasATurnException e) {
			fail("assignTurn method is not working correctly, is throwing an exception when it shouldn't");
		}catch(UserNotFoundException e) {
			fail("assignTurn method is not working correctly, is throwing an exception when it shouldn't");
		}
		
		try {
			cs.assignTurn("1006345256", User.CC);
		}catch(UserAlreadyHasATurnException e) {
			assertTrue("the exception message is not the correct",e.getMessage().equalsIgnoreCase("The user already has an active turn: " +"\n Name:David Steven\n" +" type Id: cedula de ciudadania\n" + " id: 1006345256\n" +" turn number: A00"));
			System.out.println(e.getMessage());
		}catch(UserNotFoundException e) {
			fail("assignTurn method is not working correctly, is throwing an exception when it shouldn't");
		}
		
		try {
			cs.assignTurn("1006309297", User.CC);
		}catch(UserAlreadyHasATurnException e) {
			fail("assignTurn method is not working correctly, is throwing an exception when it shouldn't");
		}catch(UserNotFoundException e) {
			fail("assignTurn method is not working correctly, is throwing an exception when it shouldn't");
		}
		
		String[] string = {"A00","A01"}; 
		
		for(int i=0;i<2;i++) {
		
			assertFalse(!(cs.getUsers().get(i).getTurn().getNumber().equalsIgnoreCase(string[i])),"assignTurn method is not sorting the usert correctly");
		}
		
	}
	
	@Test
	public void attendTurnTest() {
		setup1();
		try {
			cs.addNewUser(User.CC, "1006309297", "Marco Fidel", "Vasquez Rivera", "333-444", "3163886825");
			cs.addNewUser(User.CC, "1006345256", "David Steven", "Montoya Parra", "666-888", "3164574563");
			cs.addNewUser(User.CC, "1006368467", "Alejandro", "Fonseca Forero", "111-000", "3246853460");
			cs.assignTurn("1006345256", User.CC);
			cs.assignTurn("1006309297", User.CC);
			cs.attendTurn(Turn.ATTENDED);
		}catch(UserAlreadyExistException e) {
			fail("attendTurn method is not working correctly, is throwing an exception when it shouldn't");
		}catch(UserAlreadyHasATurnException e) {
			fail("attendTurn method is not working correctly, is throwing an exception when it shouldn't");
		}catch(UserNotFoundException e) {
			fail("attendTurn method is not working correctly, is throwing an exception when it shouldn't");
		}catch(ThereAreNoTurnsForAttendException e) {
			fail("attendTurn method is not working correctly, is throwing an exception when it shouldn't");
		}
		
		assertFalse(!cs.getUsers().get(0).getTurn().getNumber().equalsIgnoreCase("A01"),"the method attend turn is not working correctly");
		try {
			cs.attendTurn(Turn.ATTENDED);
			cs.attendTurn(Turn.ATTENDED);
			fail("attendTurn is not working correctly");
		}catch(ThereAreNoTurnsForAttendException e) {
			
		}

	}
	
	@Test
	public void showNextTurnTest() {
		setup1();
		try {
			cs.addNewUser(User.CC, "1006309297", "Marco Fidel", "Vasquez Rivera", "333-444", "3163886825");
			cs.addNewUser(User.CC, "1006345256", "David Steven", "Montoya Parra", "666-888", "3164574563");
			cs.addNewUser(User.CC, "1006368467", "Alejandro", "Fonseca Forero", "111-000", "3246853460");
			cs.assignTurn("1006345256", User.CC);
			cs.assignTurn("1006309297", User.CC);
			cs.attendTurn(Turn.ATTENDED);
			assertTrue("the showNextTurn method is not working correctly",cs.showNextTurn().equalsIgnoreCase("A01"));
		}catch(UserAlreadyExistException e) {
			fail("the method is not working correctly, is throwing an exception when it shouldn't");
		}catch(UserAlreadyHasATurnException e) {
			fail("the method is not working correctly, is throwing an exception when it shouldn't");
		}catch(UserNotFoundException e) {
			fail("the method is not working correctly, is throwing an exception when it shouldn't");
		}catch(ThereAreNoTurnsForAttendException e) {
			fail("the method is not working correctly, is throwing an exception when it shouldn't");
		}
		
		try {
			cs.attendTurn(Turn.ATTENDED);	
		}catch(ThereAreNoTurnsForAttendException e) {
			fail("the method is not working correctly, is throwing an exception when it shouldn't");
		}
		
		try {
			assertTrue("the showNextTurn method is not working correctly",cs.showNextTurn().equalsIgnoreCase("there are no turns for attend at this moment"));
		}catch(ThereAreNoTurnsForAttendException e) {
			
		}
	}
}
