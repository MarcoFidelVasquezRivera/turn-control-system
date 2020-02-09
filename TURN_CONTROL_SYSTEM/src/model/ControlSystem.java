package model;

import java.util.ArrayList;

import customExceptions.UserAlreadyExistException;
import customExceptions.UserAlreadyHasATurnException;
import customExceptions.UserNotFoundException;

public class ControlSystem {
//+++++++++++++++++++++++++++++++++++
//	 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Turn> turnsAttended = new ArrayList<Turn>();
	private char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
//+++++++++++++++++++++++++++++++++++
//	  METHODS
//+++++++++++++++++++++++++++++++++++
	public ControlSystem() {
		
	}
	
	
	public void addNewUser(String typeId, String id, String firstNames, String lastNames, String adress, String telephone) throws UserAlreadyExistException {
		if(users.isEmpty()) {
			users.add(new User(typeId,id,firstNames,lastNames,adress,telephone));
		}else {
			
			for(int i=0;0<users.size();i++) {
				if(users.get(i).getId().equalsIgnoreCase(id) && users.get(i).getTypeId().equalsIgnoreCase(typeId)) {
					throw new UserAlreadyExistException(id,firstNames+" "+lastNames,typeId);
				}
			}
			users.add(new User(typeId,id,firstNames,lastNames,adress,telephone));
		}
	}
	
	
	public String searchUser(String id, String typeId) throws UserNotFoundException {
		boolean flag=true;
		String message="";
		if(users.isEmpty()) {
			throw new UserNotFoundException(id,typeId);
		}else {
			for(int i=0;i<users.size();i++) {
				if(users.get(i).getId().equalsIgnoreCase(id) && users.get(i).getTypeId().equalsIgnoreCase(id)) {
					message=users.get(i).toString();
					flag=false;
				}
			}
			if(flag) {
				throw new UserNotFoundException(id,typeId);
			}
		}
		return message;
	}
	
	
	public void assignTurn(String id, String typeId) throws UserNotFoundException, UserAlreadyHasATurnException {
		searchUser(id,typeId);
		
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getId().equalsIgnoreCase(id) && users.get(i).getTypeId().equalsIgnoreCase(typeId) && users.get(i).getTurn()!=null) {
				throw new UserAlreadyHasATurnException(id,typeId,users.get(i).getFistNames(),users.get(i).getTurn().getNumber());
				
			}else if(users.get(i).getId().equalsIgnoreCase(id) && users.get(i).getTypeId().equalsIgnoreCase(typeId) && users.get(i).getTurn()==null) {
				
				users.get(i).setTurn(String number,users.get(i).getFistNames(),id,Turn.NOT_ATTENDED_YET);
			}
		}
	}
	
	
}
