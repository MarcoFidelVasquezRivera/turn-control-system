package model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class UserTest {

	private User user;
	private Turn turn;
	private TurnType tt;
	
	@Test
	public void testUser() {
		String ttName = "arargi";
		double ttTime = 3.0; 
		tt = new TurnType(ttName,ttTime);
		String turnNumber = "A00";
		String userFirstName = "Marco Fidel"; 
		String userLastName = "Vasquez Rivera";
		String userName = "Marco Fidel Vasquez Rivera";
		String userId = "1006309297";
		String userStatus = Turn.NOT_ATTENDED_YET;
		TurnType turnType = tt;
		String typeId = User.CC;
		String userAddress = "333-444";
		String userPhone = "3163886825";
		
		turn = new Turn(turnNumber, userName, userId, userStatus, turnType, typeId);
		user = new User(typeId, userId, userFirstName, userLastName, userAddress, userPhone);
	
		assertEquals("firstnames are not equals",userFirstName,user.getFirstNames());
		assertEquals("lastnames are not equals",userLastName,user.getLastNames());
		assertEquals("typeId are not equals",typeId,user.getTypeId());
		assertEquals("userId are not equals",userId,user.getId());
		assertEquals("address are not equals",userAddress,user.getAddress());
		assertEquals("phones are not equals",userPhone,user.getTelephone());
		assertTrue("active turn should be false",user.getActiveTurn()==false);
		assertNull("getTurn is differend from null",user.getTurn());
		assertTrue("arrayList of turn should be empty",user.getArrayTurn().isEmpty());
		
		DateTime dt = user.getDt();
		assertEquals("seconds are not zero",0,dt.getSeconds());
		assertEquals("minutes are not zero",0,dt.getMinutes());
		assertEquals("hour is not zero",0,dt.getHour());
		assertEquals("days are not zero",0,dt.getDays());
		assertEquals("months are not zero",0,dt.getMonths());
		assertEquals("years are not zero",0,dt.getYears());
		
		int seconds = 4;
		int minutes = 20;
		int hour = 1;
		int days = 20;
		int month = 3;
		int year = 2020;
		
		user.setDt(seconds, minutes, hour, days, month, year);
		dt = user.getDt();
		
		assertEquals("seconds are not equals",seconds,dt.getSeconds());
		assertEquals("minutes are not equals",minutes,dt.getMinutes());
		assertEquals("hour are not equals",hour,dt.getHour());
		assertEquals("days are not equals",days,dt.getDays());
		assertEquals("months are not equals",month,dt.getMonths());
		assertEquals("years are not equals",year,dt.getYears());
		
		user.setTurn(turn);
		assertTrue("arrayList of turn should be empty",user.getArrayTurn().isEmpty()==false);
		assertTrue("getTurn is differend from null",user.getTurn()!=null);
		assertTrue("getTurn is differend from null",user.getTurn()==turn);
		assertTrue("active turn should be false",user.getActiveTurn()==true);
		assertEquals("user status in the turn should be 'not attended yet'.",Turn.NOT_ATTENDED_YET,user.getTurn().getUserStatus());
		
		turn.setUserStatus(Turn.ATTENDED);
		assertTrue("active turn should be false",user.getActiveTurn()==false);
		assertNull("getTurn is differend from null",user.getTurn());
		
		String turn2number = "A01";
		//turn = new Turn(turnNumber, userName, userId, userStatus, turnType, typeId);
		Turn turn2 = new Turn(turn2number, userName, userId, userStatus, turnType, typeId);
		user.setTurn(turn2);
		
		assertTrue("arrayList of turn should be empty",user.getArrayTurn().isEmpty()==false);
		assertTrue("arrayList's size should be 2",user.getArrayTurn().size()==2);
		assertTrue("firstTurn is different",user.getArrayTurn().get(0)==turn);
		assertTrue("secondTurn is different",user.getArrayTurn().get(1)==turn2);
		assertTrue("active turn should be false",user.getActiveTurn()==true);
		assertTrue("getTurn is differend from null",user.getTurn()==turn2);
		assertEquals("user status in the turn should be 'not attended yet'.",Turn.NOT_ATTENDED_YET,user.getTurn().getUserStatus());
		
		turn2.setUserStatus(Turn.ATTENDED);
		assertTrue("active turn should be false",user.getActiveTurn()==false);
		assertNull("getTurn is differend from null",user.getTurn());
		
	}
}
