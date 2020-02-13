package model;

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
	private String typeId;
	private String id;
	private String firstNames;
	private String lastNames;
	private String adress; 
	private String telephone;
	private Turn turn = null;
//+++++++++++++++++++++++++++++++++++
//        	  METHODS
//+++++++++++++++++++++++++++++++++++
	public User(String typeId, String id, String firstNames, String lastNames, String adress, String telephone) {
		this.typeId=typeId;
		this.id=id;
		this.firstNames=firstNames;
		this.lastNames=lastNames;
		this.adress=adress;
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
	
	public String getFistNames() {
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
	
	public String getAdress() {
		return adress;
	}
	
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Turn getTurn() {
		return turn;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
	}
	
	public void setTurn(String number, String userName, String userId, String userStatus) {
		this.turn = new Turn(number,userName,userId,userStatus);
	}
	
}
