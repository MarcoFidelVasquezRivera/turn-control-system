package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TurnTypeTest {
	private TurnType tt;
	
	@Test
	void testTurnType() {
		String name = "este lab me puso a llorar";
		double time = 3.7;
		tt = new TurnType(name,time);
		
		assertEquals("name are not equals",name,tt.getName());
		assertTrue("time are not equals",time==tt.getMinutesDelay());
	}

}
