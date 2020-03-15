package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DateTimeTest {
	private DateTime dt;
	
	@Test
	void testDateTime() {
		int seconds = 4;
		int minutes = 20;
		int hour = 1;
		int days = 20;
		int month = 3;
		int year = 2020;
		
		dt = new DateTime();
		
		assertEquals("seconds are not zero",0,dt.getSeconds());
		assertEquals("minutes are not zero",0,dt.getMinutes());
		assertEquals("hour is not zero",0,dt.getHour());
		assertEquals("days are not zero",0,dt.getDays());
		assertEquals("months are not zero",0,dt.getMonths());
		assertEquals("years are not zero",0,dt.getYears());
		
		dt.setSeconds(seconds);
		dt.setMinutes(minutes);
		dt.setHour(hour);
		dt.setDays(days);
		dt.setMonths(month);
		dt.setYears(year);
		
		assertEquals("seconds are not equals",seconds,dt.getSeconds());
		assertEquals("minutes are not equals",minutes,dt.getMinutes());
		assertEquals("hour are not equals",hour,dt.getHour());
		assertEquals("days are not equals",days,dt.getDays());
		assertEquals("months are not equals",month,dt.getMonths());
		assertEquals("years are not equals",year,dt.getYears());	
	}

}
