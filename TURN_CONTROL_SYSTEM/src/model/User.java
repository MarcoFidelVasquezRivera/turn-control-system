package model;

import java.util.ArrayList;

public class User {
//+++++++++++++++++++++++++++++++++++
//	         CONSTANTS
//+++++++++++++++++++++++++++++++++++
	public final static String TI ="targeta de identidad";
	public final static String CC ="cedula de ciudadania";
	public final static String RC ="registro civil";
	public final static String PASSPORT ="pasaporte";
	public final static String FOREIGN_IDENTITY_CARD="cadula de ciudadania extrangera";
//+++++++++++++++++++++++++++++++++++
//    		 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	private ArrayList<Turn> turn = new ArrayList<Turn>();
	private DateTime dt = new DateTime();
	private String typeId;
	private String id;
	private String firstNames;
	private String lastNames;
	private String address; 
	private String telephone;
//+++++++++++++++++++++++++++++++++++
//        	  METHODS
//+++++++++++++++++++++++++++++++++++
	public User(String typeId, String id, String firstNames, String lastNames, String address, String telephone) {
		this.typeId=typeId;
		this.id=id;
		this.firstNames=firstNames;
		this.lastNames=lastNames;
		this.address=address;
		this.telephone=telephone;
	}
	
	public String getTypeId() {
		return typeId;
	}
	
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirstNames() {
		return firstNames;
	}
	
	public void setFistNames(String fistNames) {
		this.firstNames = fistNames;
	}
	
	public String getLastNames() {
		return lastNames;
	}
	
	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String adress) {
		this.address = adress;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public boolean getActiveTurn() {
		boolean activeTurn = false;
		
		for(int i=0;i<turn.size();i++) {
			if(turn.get(i).getUserStatus().equalsIgnoreCase(Turn.NOT_ATTENDED_YET)) {
				activeTurn=true;
			}
		}
		
		return activeTurn;
	}
	
	public Turn getTurn() {
		Turn activeTurn=null;
		
		for(int i=0;i<turn.size();i++) {
			if(turn.get(i).getUserStatus().equalsIgnoreCase(Turn.NOT_ATTENDED_YET)) {
				activeTurn=turn.get(i);
			}
		}
		
		return activeTurn;
	}

	public void setTurn(Turn turn) {
		this.turn.add(turn);
	}
	
	public ArrayList<Turn> getArrayTurn(){
		return turn;
	}
	
	/**
	 * <b>Name:</b> toString.<br>
	 * This method returns the information of the user.<br>
	 * @return a message with the information of the user.<br>
	*/
	public String toString() {
		return "type ID: "+typeId+"\nID: "+id+"\nfirst names: "+firstNames+"\nlast names: "+lastNames+"\naddress: "+address+"\ntelephone: "+telephone;
	}
	
	public DateTime getDt() {
		return dt;
	}
	
	public void setDt(int s, int m,int h,int d,int mo,int y) {
		dt.setSeconds(s);
		dt.setMinutes(m);
		dt.setHour(h);
		dt.setDays(d);
		dt.setMonths(mo);
		dt.setYears(y);
	}
	
}
