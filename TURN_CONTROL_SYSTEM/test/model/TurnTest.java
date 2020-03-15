package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TurnTest {
	Turn turn;
	TurnType tt;
	
	@Test
	public void testUser() {
		String ttName = "arargi";
		double ttTime = 3.0; 
		tt = new TurnType(ttName,ttTime);
		//String number, String userName, String userId, String userStatus, TurnType turnType, String typeId
		String turnNumber = "A00";
		String userName = "Marco Fidel Vasquez Rivera";
		String userId = "1006309297";
		String userStatus = Turn.NOT_ATTENDED_YET;
		TurnType turnType = tt;
		String typeId = User.CC;
		
		turn = new Turn(turnNumber, userName, userId, userStatus, turnType, typeId);
		
		assertEquals("turnNumber are not equals",turnNumber,turn.getNumber());
		assertEquals("userName are not equals",userName,turn.getUserName());
		assertEquals("userId are not equals",userId,turn.getUserId());
		assertEquals("userStatus are not equals",userStatus,turn.getUserStatus());
		assertEquals("userStartus are not equals",typeId,turn.getUserTypeId());
		assertTrue("turnType are not equals",turnType==turn.getTurnType());
	}
	
	@Test
	public void testUser2() {
		String ttName = "arargi";
		double ttTime = 3.0; 
		tt = null;
		//String number, String userName, String userId, String userStatus, TurnType turnType, String typeId
		String turnNumber = "A00";
		String userName = "Marco Fidel Vasquez Rivera";
		String userId = "1006309297";
		String userStatus = Turn.NOT_ATTENDED_YET;
		TurnType turnType = tt;
		String typeId = User.CC;
		
		turn = new Turn(turnNumber, userName, userId, userStatus, turnType, typeId);
		
		assertEquals("turnNumber are not equals",turnNumber,turn.getNumber());
		assertEquals("userName are not equals",userName,turn.getUserName());
		assertEquals("userId are not equals",userId,turn.getUserId());
		assertEquals("userStatus are not equals",userStatus,turn.getUserStatus());
		assertEquals("usertypeId are not equals",typeId,turn.getUserTypeId());
		assertNull("turnType is different from null", turn.getTurnType());
	}

}
