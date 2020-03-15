package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import customExceptions.ThereAreNoTurnsForAttendException;
import customExceptions.ThereIsNotTurnTypeException;
import customExceptions.TurnAlreadyExistException;
import customExceptions.TurnTypeAlreadyExistException;
import customExceptions.UserAlreadyExistException;
import customExceptions.UserAlreadyHasATurnException;
import customExceptions.UserNotFoundException;

class ControlSystemTest {

	private ControlSystem cs;
	private TurnType tt;
	
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
			fail("the method addNewUser is adding a user that already exist");
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
	public void updateTimeTest() {
		setup1();
		Calendar calendar = new GregorianCalendar(); 
		
		int seconds = calendar.get(Calendar.SECOND);
		int minutes = calendar.get(Calendar.MINUTE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int days = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		cs.updateDate();
		cs.updateDate(seconds, minutes, hour, days, month, year);
		DateTime DateTimeDiff = cs.getDateTimeDiff();
		
		assertEquals("seconds are not equals",0,DateTimeDiff.getSeconds());
		assertEquals("minutes are not equals",0,DateTimeDiff.getMinutes());
		assertEquals("hour are not equals",0,DateTimeDiff.getHour());
		assertEquals("days are not equals",0,DateTimeDiff.getDays());
		assertEquals("months are not equals",0,DateTimeDiff.getMonths());
		assertEquals("years are not equals",0,DateTimeDiff.getYears());
		 
		seconds = 1+calendar.get(Calendar.SECOND);
		minutes = 1+calendar.get(Calendar.MINUTE);
		hour = 1+calendar.get(Calendar.HOUR_OF_DAY);
		days = 1+calendar.get(Calendar.DAY_OF_MONTH);
		month = 1+calendar.get(Calendar.MONTH)+1;
		year = 1+calendar.get(Calendar.YEAR);
		
		cs.updateDate();
		cs.updateDate(seconds, minutes, hour, days, month, year);
		DateTimeDiff = cs.getDateTimeDiff();
		
		assertEquals("seconds are not equals",1,DateTimeDiff.getSeconds());
		assertEquals("minutes are not equals",1,DateTimeDiff.getMinutes());
		assertEquals("hour are not equals",1,DateTimeDiff.getHour());
		assertEquals("days are not equals",1,DateTimeDiff.getDays());
		assertEquals("months are not equals",1,DateTimeDiff.getMonths());
		assertEquals("years are not equals",1,DateTimeDiff.getYears());	
	}
	
	@Test
	public void assignTurnTest() {
		setup1();
		String ttName = "almuerzo";
		double ttTime = 4.0;
		try {
			cs.addNewUser(User.CC, "1006309297", "Marco Fidel", "Vasquez Rivera", "333-444", "3163886825");
			cs.addNewUser(User.CC, "1006345256", "David Steven", "Montoya Parra", "666-888", "3164574563");
			cs.addNewUser(User.CC, "1006368467", "Alejandro", "Fonseca Forero", "111-000", "3246853460");
			tt = new TurnType(ttName,ttTime);
			cs.assignTurn("1006345256", User.CC,tt);
		}catch(UserAlreadyExistException e) {
			fail("assignTurn method is not working correctly, is throwing an exception when it shouldn't");
		}catch(UserAlreadyHasATurnException e) {
			fail("assignTurn method is not working correctly, is throwing an exception when it shouldn't");
		}catch(UserNotFoundException e) {
			fail("assignTurn method is not working correctly, is throwing an exception when it shouldn't");
		}
		
		Calendar calendar = new GregorianCalendar(); 
		
		int seconds = 1+calendar.get(Calendar.SECOND);
		int minutes = 1+calendar.get(Calendar.MINUTE);
		int hour = 1+calendar.get(Calendar.HOUR_OF_DAY);
		int days = 1+calendar.get(Calendar.DAY_OF_MONTH);
		int month = 1+calendar.get(Calendar.MONTH)+1;
		int year = 1+calendar.get(Calendar.YEAR);
		
		cs.updateDate(seconds, minutes, hour, days, month, year);
		
		Turn at = cs.getTurns().get(0);

		assertEquals("turn was not assign correctly", tt, at.getTurnType());
		assertEquals("turn was not assign correctly",at.getUserId(),"1006345256");
		
		at = cs.getUsers().get(1).getTurn();
		assertTrue("turn was not assign correctly to user",tt == at.getTurnType());
		assertTrue("turn was not assign correctly to user",at.getUserId().equalsIgnoreCase("1006345256"));
		
		
		
		try {
			cs.assignTurn("1006345256", User.CC,tt);
		}catch(UserAlreadyHasATurnException e) {
			assertTrue("the exception message is not the correct",e.getMessage().equalsIgnoreCase("The user already has an active turn: " +"\n Name:David Steven\n" +" type Id: cedula de ciudadania\n" + " id: 1006345256\n" +" turn number: A00"));
		}catch(UserNotFoundException e) {
			fail("assignTurn method is not working correctly, is throwing an exception when it shouldn't");
		}
		
		try {
			cs.assignTurn("1006309297", User.CC,tt);
		}catch(UserAlreadyHasATurnException e) {
			fail("assignTurn method is not working correctly, is throwing an exception when it shouldn't");
		}catch(UserNotFoundException e) {
			fail("assignTurn method is not working correctly, is throwing an exception when it shouldn't");
		}
				
	}
	
	@Test
	public void attendTurnTest() {
		setup1();
		String ttName = "almuerzo";
		double ttTime = 4.0;
		tt = new TurnType(ttName,ttTime);
		
		try {
			cs.addNewUser(User.CC, "1006309297", "Marco Fidel", "Vasquez Rivera", "333-444", "3163886825");
			cs.addNewUser(User.CC, "1006345256", "David Steven", "Montoya Parra", "666-888", "3164574563");
			cs.addNewUser(User.CC, "1006368467", "Alejandro", "Fonseca Forero", "111-000", "3246853460");
			cs.assignTurn("1006345256", User.CC,tt);
			cs.assignTurn("1006309297", User.CC,tt);
			cs.attendTurn();
			assertEquals("attendTurn is not setting turns in turnsAttended",1,cs.getTurnsAttended().size());
			String status = cs.getTurnsAttended().get(0).getUserStatus();
			assertTrue("attendTurn is not changing the userStatus of the turn",(status.equalsIgnoreCase(Turn.ATTENDED) || status.equalsIgnoreCase(Turn.USER_WAS_NOT)));
		}catch(UserAlreadyExistException e) {
			fail("attendTurn method is not working correctly, is throwing an exception when it shouldn't");
		}catch(UserAlreadyHasATurnException e) {
			fail("attendTurn method is not working correctly, is throwing an exception when it shouldn't");
		}catch(UserNotFoundException e) {
			fail("attendTurn method is not working correctly, is throwing an exception when it shouldn't");
		}catch(ThereAreNoTurnsForAttendException e) {
			fail("attendTurn method is not working correctly, is throwing an exception when it shouldn't");
		}
		
		assertFalse(!cs.getTurns().get(0).getNumber().equalsIgnoreCase("A01"),"the method attend turn is not working correctly");
		try {
			cs.attendTurn();
			cs.attendTurn();
			fail("attendTurn is not working correctly");
		}catch(ThereAreNoTurnsForAttendException e) {
			
		}
	}
	
	@Test
	public void showNextTurnTest() {
		setup1();
		String ttName = "almuerzo";
		double ttTime = 4.0;
		tt = new TurnType(ttName,ttTime);
		try {
			cs.addNewUser(User.CC, "1006309297", "Marco Fidel", "Vasquez Rivera", "333-444", "3163886825");
			cs.addNewUser(User.CC, "1006345256", "David Steven", "Montoya Parra", "666-888", "3164574563");
			cs.addNewUser(User.CC, "1006368467", "Alejandro", "Fonseca Forero", "111-000", "3246853460");
			cs.assignTurn("1006345256", User.CC,tt);
			cs.assignTurn("1006309297", User.CC,tt);
			cs.attendTurn();
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
			cs.attendTurn();	
		}catch(ThereAreNoTurnsForAttendException e) {
			fail("the method is not working correctly, is throwing an exception when it shouldn't");
		}
		
		try {
			assertTrue("the showNextTurn method is not working correctly",cs.showNextTurn().equalsIgnoreCase("there are no turns for attend at this moment"));
		}catch(ThereAreNoTurnsForAttendException e) {
			
		}
	}
	
	@Test
	public void generateTurnTypeTest() {
		setup1();
		String name1 = "almuerzo";
		double time1 = 3.0;
		String name2 = "desayuno";
		double time2 = 2.0;
		//public void generateTurnType(String name, double delay) throws TurnAlreadyExistException{
		try {
			cs.generateTurnType(name1,time1);
		}catch(TurnTypeAlreadyExistException e) {
			fail("generateTurnType is not working correctly");
		}
		
		TurnType tt = cs.getTurnsType().get(0);
		assertEquals("names are not equals",tt.getName(),name1);
		assertTrue("times are not equals",tt.getMinutesDelay()==time1);
		
		try {
			cs.generateTurnType(name1,time1);
			fail("generateTurnType is creating the same turn when it should not");
		}catch(TurnTypeAlreadyExistException e) {
			
		}
		
		try {
			cs.generateTurnType(name2,time2);
		}catch(TurnTypeAlreadyExistException e) {
			fail("generateTurnType is not working correctly");
		}
		
		tt = cs.getTurnsType().get(1);
		assertEquals("names are not equals",tt.getName(),name2);
		assertTrue("times are not equals",tt.getMinutesDelay()==time2);
	}
	
	@Test
	public void generateRandomTurnTest() {
		setup1();
		String name1 = "almuerzo";
		double time1 = 3.0;
		String name2 = "desayuno";
		double time2 = 2.0;
		
		
		try {
			cs.addNewUser(User.CC, "1006309297", "Marco Fidel", "Vasquez Rivera", "333-444", "3163886825");
			cs.addNewUser(User.CC, "1006345256", "David Steven", "Montoya Parra", "666-888", "3164574563");
			cs.addNewUser(User.CC, "1006368467", "Alejandro", "Fonseca Forero", "111-000", "3246853460");
		} catch (UserAlreadyExistException e1) {
			fail();
		}
		
		try {
			cs.generateTurnType(name1,time1);
			cs.generateTurnType(name2,time2);
		}catch(TurnTypeAlreadyExistException e) {
			fail("generateTurnType is not working correctly");
		}
		
		
		try {
			cs.generateRandomTurn();
		} catch (UserNotFoundException | UserAlreadyHasATurnException | ThereIsNotTurnTypeException e) {
		}
		
		Turn at = cs.getTurns().get(0);
		assertTrue("generateRandomTurn is not creating a turn",at!=null);
		assertTrue("generateRandomTurn is not creating correctly turns",!(at.getNumber().equalsIgnoreCase(" ") && at.getNumber()==null &&  at.getNumber().equalsIgnoreCase("")));
		do {
			try {
				cs.generateRandomTurn();
			} catch (UserNotFoundException | UserAlreadyHasATurnException | ThereIsNotTurnTypeException e) {
		
			}
		}while(cs.getTurns().size()<2);

		at = cs.getTurns().get(1);
		assertTrue("generateRandomTurn is not creating a turn",at!=null);
		assertTrue("generateRandomTurn is not creating correctly turns",!(at.getNumber().equalsIgnoreCase(" ") && at.getNumber()==null &&  at.getNumber().equalsIgnoreCase("")));
	}
	
	@Test
	public void suspendUsersTest() {
		setup1();
		
		try {
			cs.addNewUser(User.CC, "1006309297", "Marco Fidel", "Vasquez Rivera", "333-444", "3163886825");
			cs.addNewUser(User.CC, "1006345256", "David Steven", "Montoya Parra", "666-888", "3164574563");
			cs.addNewUser(User.CC, "1006368467", "Alejandro", "Fonseca Forero", "111-000", "3246853460");
		} catch (UserAlreadyExistException e1) {
			fail();
		}
		
		
		try {
			for(int i=0;i<20;i++) {
				cs.generateRandomTurn();
				cs.attendTurn();
			}
			
		} catch (UserNotFoundException | UserAlreadyHasATurnException | ThereIsNotTurnTypeException | ThereAreNoTurnsForAttendException e) {
			
		}
		
		cs.suspendUsers();
		
		setup1();
		String ttName = "almuerzo";
		double ttTime = 4.0;
		tt = new TurnType(ttName,ttTime);

		try {
			cs.assignTurn("1006345256", User.CC,tt);
			cs.assignTurn("1006309297", User.CC,tt);
			cs.assignTurn("1006368467", User.CC,tt);
			fail("assignTurn is assigning even when there are users suspended");
		} catch (UserNotFoundException | UserAlreadyHasATurnException e) {

		}
	}
	
	@Test
	public void attendTurnsUntillActualDateTest() {
		setup1();
		String name1 = "almuerzo";
		double time1 = 3.0;
		String name2 = "desayuno";
		double time2 = 2.0;
		tt = new TurnType(name2,time2);
		
		try {
			cs.addNewUser(User.CC, "1006309297", "Marco Fidel", "Vasquez Rivera", "333-444", "3163886825");
			cs.addNewUser(User.CC, "1006345256", "David Steven", "Montoya Parra", "666-888", "3164574563");
			cs.addNewUser(User.CC, "1006368467", "Alejandro", "Fonseca Forero", "111-000", "3246853460");
		} catch (UserAlreadyExistException e1) {
			fail();
		}
		
		try {
			cs.generateTurnType(name1,time1);
			cs.generateTurnType(name2,time2);
		}catch(TurnTypeAlreadyExistException e) {
			fail("generateTurnType is not working correctly");
		}
		
		try {
			cs.assignTurn("1006345256", User.CC,tt);
			cs.assignTurn("1006309297", User.CC,tt);
			cs.assignTurn("1006368467", User.CC,tt);
		} catch (UserNotFoundException | UserAlreadyHasATurnException e) {

		}
		
		Calendar calendar = new GregorianCalendar(); 
		
		int seconds = calendar.get(Calendar.SECOND);
		int minutes = 3+calendar.get(Calendar.MINUTE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int days = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		cs.updateDate();
		cs.setLastAttended();
		cs.updateDate(seconds, minutes, hour, days, month, year);
		
		try {
			cs.attendTurnsUntillActualDate();
		} catch (ThereAreNoTurnsForAttendException e) {
			fail("attend turn is not finding the turns");
		}
		
		int tSize = cs.getTurns().size();
		int taSize = cs.getTurnsAttended().size();
		
		assertTrue("method is not attending the turns correctly .",tSize==2);
		assertTrue("method is not attending the turns correctly ..",taSize==1);
		
		seconds = calendar.get(Calendar.SECOND);
		minutes = 10+calendar.get(Calendar.MINUTE);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		days = calendar.get(Calendar.DAY_OF_MONTH);
		month = calendar.get(Calendar.MONTH)+1;
		year = calendar.get(Calendar.YEAR);
		
		cs.updateDate();
		cs.updateDate(seconds, minutes, hour, days, month, year);
		
		try {
			cs.attendTurnsUntillActualDate();
		} catch (ThereAreNoTurnsForAttendException e) {
			fail("attend turn is not finding the turns");
		}
		
		try {
			cs.attendTurnsUntillActualDate();
		} catch (ThereAreNoTurnsForAttendException e) {
			fail("attend turn is not finding the turns");
		}
		
		tSize = cs.getTurns().size();
		taSize = cs.getTurnsAttended().size();
		
		assertTrue("method is not attending the turns correctly .",tSize==0);
		assertTrue("method is not attending the turns correctly ..",taSize==3);
		
	}
	
	@Test
	public void attendTurnsUntillActualDateTest2() {
		setup1();
		String name1 = "almuerzo";
		double time1 = 3.0;
		String name2 = "desayuno";
		double time2 = 2.0;
		tt = new TurnType(name2,time2);
		
		try {
			cs.addNewUser(User.CC, "1006309297", "Marco Fidel", "Vasquez Rivera", "333-444", "3163886825");
			cs.addNewUser(User.CC, "1006345256", "David Steven", "Montoya Parra", "666-888", "3164574563");
			cs.addNewUser(User.CC, "1006368467", "Alejandro", "Fonseca Forero", "111-000", "3246853460");
		} catch (UserAlreadyExistException e1) {
			fail();
		}
		
		try {
			cs.generateTurnType(name1,time1);
			cs.generateTurnType(name2,time2);
		}catch(TurnTypeAlreadyExistException e) {
			fail("generateTurnType is not working correctly");
		}
		
		try {
			cs.assignTurn("1006345256", User.CC,tt);
			cs.assignTurn("1006309297", User.CC,tt);
			cs.assignTurn("1006368467", User.CC,tt);
		} catch (UserNotFoundException | UserAlreadyHasATurnException e) {

		}
		
		Calendar calendar = new GregorianCalendar(); 
		
		int seconds = calendar.get(Calendar.SECOND);
		int minutes = 1+calendar.get(Calendar.MINUTE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int days = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		cs.updateDate();
		cs.setLastAttended();
		cs.updateDate(seconds, minutes, hour, days, month, year);
		
		try {
			cs.attendTurnsUntillActualDate();
		} catch (ThereAreNoTurnsForAttendException e) {
			fail("attend turn is not finding the turns");
		}
		
		int tSize = cs.getTurns().size();
		int taSize = cs.getTurnsAttended().size();
		
		assertTrue("method is not attending the turns correctly .",tSize==3);
		assertTrue("method is not attending the turns correctly ..",taSize==0);
	}
}
