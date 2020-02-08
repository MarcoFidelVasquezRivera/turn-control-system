package model;

public class User {
//+++++++++++++++++++++++++++++++++++
//	         CONSTANTS
//+++++++++++++++++++++++++++++++++++
	public static String TI ="targeta de identidad";
	public static String CC ="cedula de ciudadania";
	public static String RC ="registro civil";
	public static String PASSPORT ="pasaporte";
	public static String FOREIGN_IDENTITY_CARD="foreign identity card";
//+++++++++++++++++++++++++++++++++++
//    		 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	private String typeId;
	private String id;
	private String fistNames;
	private String lastNames;
	private String adress; 
	private String telephone;
//+++++++++++++++++++++++++++++++++++
//        	  METHODS
//+++++++++++++++++++++++++++++++++++
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
		return fistNames;
	}
	
	public void setFistNames(String fistNames) {
		this.fistNames = fistNames;
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
	
}
