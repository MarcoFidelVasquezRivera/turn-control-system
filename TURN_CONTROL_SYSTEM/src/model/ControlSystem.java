package model;

import java.util.ArrayList;

import customExceptions.ThereAreNoTurnsForAttendException;
import customExceptions.UserAlreadyExistException;
import customExceptions.UserAlreadyHasATurnException;
import customExceptions.UserNotFoundException;

public class ControlSystem {
//+++++++++++++++++++++++++++++++++++
//	 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Turn> turnsAttended = new ArrayList<Turn>();
	private int letter;
	private int nOne;
	private int nTwo;
	private char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
//+++++++++++++++++++++++++++++++++++
//	  METHODS
//+++++++++++++++++++++++++++++++++++
	public ControlSystem() {
		letter=0;
		nOne=0;
		nTwo=0;
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
	
	
	public String assignTurn(String id, String typeId) throws UserNotFoundException, UserAlreadyHasATurnException {
		searchUser(id,typeId);
		String message="";
		boolean flag=false;
		User auxUser=null;
		
		for(int i=0;i<users.size() && !flag;i++) {
			if(users.get(i).getId().equalsIgnoreCase(id) && users.get(i).getTypeId().equalsIgnoreCase(typeId) && users.get(i).getTurn()!=null) {
				throw new UserAlreadyHasATurnException(id,typeId,users.get(i).getFistNames(),users.get(i).getTurn().getNumber());
				
			}else if(users.get(i).getId().equalsIgnoreCase(id) && users.get(i).getTypeId().equalsIgnoreCase(typeId) && users.get(i).getTurn()==null) {	
				
				users.get(i).setTurn(String.valueOf(alphabet[letter])+Integer.toString(nTwo)+Integer.toString(nOne), users.get(i).getFistNames()+users.get(i).getLastNames(), users.get(i).getId(), Turn.NOT_ATTENDED_YET);
				message="the turn has been assigned: \n"+users.get(i).getTurn().getNumber();
				
				for(int j=0;j<users.size() && users.get(j).getTurn()==null;j++) {
					//if(users.get(j).getTurn()==null) {
						auxUser = users.get(j);
						users.set(j,users.get(i));
						users.set(i, auxUser);//bring the user with the new turn until the first position
					//}                         //without a turn and exchange the two users
				}
				
				passTurn();	
				flag=true;
			}
		}
		return message;
	}
	
	
	public void passTurn() {
		nOne++;
		if(nOne>9) {
			nOne=0;
			nTwo++;
			if(nTwo>9) {
				nTwo=0;
				letter++;
				if(letter>alphabet.length-1) {
					letter=0;
				}
			}
		}
	}
	
	public String  showNextTurn() {
		return users.get(0).toString();
	}
	
	public void attendTurn(String status) throws ThereAreNoTurnsForAttendException {
		User auxUser=null;
		
		if(users.get(0).getTurn()==null) {
			throw new ThereAreNoTurnsForAttendException();
		}else {
			turnsAttended.add(users.get(0).getTurn());
			turnsAttended.get(turnsAttended.size()-1).setUserStatus(status);
			users.get(0).setTurn(null);
			
			for(int i=1;i<users.size() && users.get(i).getTurn() !=null;i++) {
				//if(users.get(i).getTurn() !=null) {
					auxUser = users.get(i);
					users.set(i-1,users.get(i));
					users.set(i, auxUser);
				//}
			}
		}
	}
	
	
	public void resetTurns() {
		letter=0;
		nOne=0;
		nTwo=0;
	}
	
}
