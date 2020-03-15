package model;

public class Turn implements Comparable<Turn>{
//+++++++++++++++++++++++++++++++++++
//    CONSTANTS
//+++++++++++++++++++++++++++++++++++
	public final static String ATTENDED="user was attended correctly";
	public final static String USER_WAS_NOT="user was not";
	public final static String NOT_ATTENDED_YET="not attended yet";
//+++++++++++++++++++++++++++++++++++
//	 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	private TurnType turnType;
	private String userTypeId;
	private String number;
	private String userName;
	private String userId;
	private String userStatus;//this attribute is for look if the user was attended or was not when they was called for attend them
//+++++++++++++++++++++++++++++++++++
//	  METHODS
//+++++++++++++++++++++++++++++++++++
	public Turn(String number, String userName, String userId, String userStatus, TurnType turnType, String typeId) {
		this.number=number;
		this.userName=userName;
		this.userId=userId;
		this.userStatus=userStatus;
		this.turnType=turnType;
		this.userTypeId=typeId;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	public String getUserTypeId() {
		return userTypeId;
	}
	
	public TurnType getTurnType() {
		return turnType;
	}

	@Override
	public int compareTo(Turn o) {
		String number1 = number;
		String number2 = o.number;
		
		int comparation;
		
		if(number1.compareTo(number2)<0) {
			comparation = -1;
		}else if(number1.compareTo(number2)>0) {
			comparation = 1;
		}else {
			comparation=0;
		}
		
		return comparation;
	}
	
}
