package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TurnType implements Serializable{
	
	public String name;
	public double minutesDelay;
	
	public TurnType(String n, double m) {
		name=n;
		minutesDelay=m;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMinutesDelay() {
		return minutesDelay;
	}

	public void setMinutesDelay(double minutesDelay) {
		this.minutesDelay = minutesDelay;
	}
	
}
