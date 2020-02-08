package model;

public class Turn {
//+++++++++++++++++++++++++++++++++++
//    CONSTANTS
//+++++++++++++++++++++++++++++++++++
	public static String ATTENDED="attended";
	public static String USER_WAS_NOT="user was not";
	public static String NOT_ATTENDED_YET="not attended yet";
//+++++++++++++++++++++++++++++++++++
//	 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	private String number;
	private String userName;
	private String userId;
	private String userStatus;//this attribute is for look if the user was attended or was not when they was called for attend them
//+++++++++++++++++++++++++++++++++++
//	  METHODS
//+++++++++++++++++++++++++++++++++++
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
	
}
