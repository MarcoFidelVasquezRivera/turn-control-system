package model;

import java.util.ArrayList;
import customExceptions.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class ControlSystem {
//+++++++++++++++++++++++++++++++++++
//	 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	public static final String[] ADDRESS_WORD= {"carrera","calle","transversal","avenida"};
	public static final String NAMES_PATH="files"+File.separator+"names.txt";
	public static final String LASTNAMES_PATH="files"+File.separator+"lastNames.txt";
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Turn> turnsAttended = new ArrayList<Turn>();
	private ArrayList<TurnType> turnType = new ArrayList<TurnType>();
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
	
	/**
	 * <b>Name:</b> addNewUser.<br>
	 * This method adds a new user, if the user already exist then return an exception.<br>
	 * <b>pre:</b> ArrayList users must be initialized.<br>
	 * <b>pos:</b> the user has been created and added or it throw an exception.<br>
	 * @param  typeId the type of the ID. <br>
	 * @param  id the number of the ID. <br>
	 * @param  firstNames the first names of the user. <br>
	 * @param  lastNames the last names of the user. <br>
	 * @param  adress the address of the user (it can be blanked). <br>
	 * @param  telephone the telephone of the user (it can be blanked). <br>
	 * @throws UserAlreadyExistException<br> 
	 * @return void<br>
	*/
	public void addNewUser(String typeId, String id, String firstNames, String lastNames, String adress, String telephone) throws UserAlreadyExistException {
		if(users.isEmpty()) {
			users.add(new User(typeId,id,firstNames,lastNames,adress,telephone));
		}else {
			for(int i=0;i<users.size();i++) {
				if(users.get(i).getId().equalsIgnoreCase(id) && users.get(i).getTypeId().equalsIgnoreCase(typeId)) {
					throw new UserAlreadyExistException(id,firstNames+" "+lastNames,typeId);
				}
			}
			users.add(new User(typeId,id,firstNames,lastNames,adress,telephone));
		}
	}
	
	/**
	 * <b>Name:</b> searchUser.<br>
	 * This method looks for a user with they id and typeId.<br>
	 * <b>pre:</b> ArrayList users must be initialized.<br>
	 * @param  typeId the type of the ID. <br>
	 * @param  id the number of the ID. <br>
	 * @throws UserNotFoundException<br> 
	 * @return returns a message with the information of the user.<br>
	*/
	public String searchUser(String id, String typeId) throws UserNotFoundException {
		boolean flag=true;
		String message="";
		
		if(users.isEmpty()) {
			throw new UserNotFoundException(id,typeId);
		}else {
			for(int i=0;i<users.size();i++) {
				if(users.get(i).getId().equalsIgnoreCase(id) && users.get(i).getTypeId().equalsIgnoreCase(typeId)) {
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
	
	/**
	 * <b>Name:</b> assignTurn.<br>
	 * This method creates a turn and give it to a user, if the user does not exist or already have a turn, then throws an exception.<br>
	 * <b>pre:</b> ArrayList users must be initialized.<br>
	 * <b>pos:</b> created a turn and gave it to a user and move the user until the first position without turn from left to right.<br>
	 * @param  typeId the type of the ID. <br>
	 * @param  id the number of the ID. <br>
	 * @throws UserNotFoundException.<br> 
	 * @throws UserAlreadyHasATurnException.<br> 
	 * @return returns the number of the turn or.<br>
	*/
	public String assignTurn(String id, String typeId) throws UserNotFoundException, UserAlreadyHasATurnException {
		searchUser(id,typeId);
		String message="";
		boolean flag=false;
		User auxUser=null;
		
		for(int i=0;i<users.size() && !flag;i++) {
	
			if(users.get(i).getId().equalsIgnoreCase(id) && users.get(i).getTypeId().equalsIgnoreCase(typeId) && users.get(i).getTurn()!=null) {
				throw new UserAlreadyHasATurnException(id,typeId,users.get(i).getFirstNames(),users.get(i).getTurn().getNumber());			
			}else if(users.get(i).getId().equalsIgnoreCase(id) && users.get(i).getTypeId().equalsIgnoreCase(typeId) && users.get(i).getTurn()==null) {	
				
				users.get(i).setTurn(String.valueOf(alphabet[letter])+Integer.toString(nTwo)+Integer.toString(nOne), users.get(i).getFirstNames()+users.get(i).getLastNames(), users.get(i).getId(), Turn.NOT_ATTENDED_YET);
				message="the turn has been assigned: \n"+users.get(i).getTurn().getNumber();
			
				for(int j=i;j>=1 && users.get(j-1).getTurn()==null;j--) {
		
						auxUser = users.get(j);
						users.set(j,users.get(j-1));
						users.set(j-1, auxUser);//bring the user with the new turn until the first position
					                          //without a turn and exchange the two users
				}
				
				passTurn();	
				flag=true;
			}
		}
		return message;
	}
	
	/**
	 * <b>Name:</b> passTurn.<br>
	 * This method passes the turn to the next turn.<br>
	 * <b>pre:</b> ArrayList users must be initialized.<br>
	 * <b>pos:</b> turn has been passed.<br>
	 * @return void.<br>
	*/
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
	
	/**
	 * <b>Name:</b> showNextTurn.<br>
	 * This method shows the next turn to attend.<br>
	 * <b>pre:</b> ArrayList users must be initialized and must have at least one user.<br>
	 * @throws ThereAreNoTurnsForAttendException.<br> 
	 * @return returns a message with next turn to attend.<br>
	*/
	public String  showNextTurn() throws ThereAreNoTurnsForAttendException {
		if(users.isEmpty()) {
			throw new ThereAreNoTurnsForAttendException();
		}
		
		if(users.get(0).getTurn()==null) {
			throw new ThereAreNoTurnsForAttendException();
		}
		
		return users.get(0).getTurn().getNumber();
	}
	
	/**
	 * <b>Name:</b> attendTurn.<br>
	 * This method attends a turn.<br>
	 * <b>pre:</b> ArrayList users and ArrayList turnsAttended must be initialized.<br>
	 * @param  status the status of the user when they was called to attend them (attended or user was not). <br>
	 * @throws ThereAreNoTurnsForAttendException.<br> 
	 * @return void.<br>
	*/
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
	
	/**
	 * <b>Name:</b> resetTurns.<br>
	 * This method resets the turn counter .<br>
	 * @return void.<br>
	*/
	public void resetTurns() {
		letter=0;
		nOne=0;
		nTwo=0;
	}
	
	public String getNumber() {
		return String.valueOf(alphabet[letter])+Integer.toString(nTwo)+Integer.toString(nOne);
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	//crear un metodo para crear un tipo de turno
	public void generateTurnType(String name, double delay) throws TurnAlreadyExistException{
		TurnType newTT = new TurnType(name,delay); 
		
		if(turnType.isEmpty()) {
			turnType.add(newTT);
		}else {
			for(int i=0;i<turnType.size();i++) {
				if(turnType.get(i).getName().equalsIgnoreCase(name)) {
					throw new TurnAlreadyExistException(name);
				}
			}
			turnType.add(newTT);
		}	
	}
	
	//crear un metodo para crear un usuario al azar
	public void generateRandomUser() throws FileNotFoundException,IOException, UserAlreadyExistException {
		String name;
		String lastNames;
		String typeId="";
		String id;
		String telephone;
		String address;
		int nId;
		Random r= new Random();
		BufferedReader brNames = new BufferedReader(new FileReader(new File(NAMES_PATH)));
		BufferedReader brLastNames = new BufferedReader(new FileReader(new File(LASTNAMES_PATH)));
		
		String[] n = brNames.readLine().split(";");
		String[] ln = brLastNames.readLine().split(";");
		
		name = n[r.nextInt(n.length)];
		lastNames = ln[r.nextInt(n.length)]+" "+ln[r.nextInt(n.length)];
		nId=r.nextInt(5);
		
		switch (nId) {
			case 0:
				typeId=User.TI;
				break;
			case 1:
				typeId=User.CC;
				break;
			case 2:
				typeId=User.RC;
				break;
			case 3:
				typeId=User.PASSPORT;
				break;
			case 4:
				typeId=User.FOREIGN_IDENTITY_CARD;
				break;
		}
		
		id=String.valueOf((long)(1000000000+(r.nextDouble()*9999999999.0)-1000000000));
		address = ADDRESS_WORD[r.nextInt(ADDRESS_WORD.length)]+" "+String.valueOf(r.nextInt(100))+"#"+String.valueOf(r.nextInt(10000));
		telephone = String.valueOf((long)(3000000000.0+(r.nextDouble()*3999999999.0)-3000000000.0));
		
		addNewUser(typeId, id, name, lastNames, address, telephone);
	}
	
	
	//crear un metodo para actualizar fecha (dos) con sobrecarga
	
	//crear un metodo para atender turnos hasta la fecha
	
	//crear un metodo para generar turnos al azar, poniendo el total de dias y el numero de turnos por dia
	
}
