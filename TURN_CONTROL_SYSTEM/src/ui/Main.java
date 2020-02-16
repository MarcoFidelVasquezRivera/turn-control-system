package ui;

import java.util.IllegalFormatException;
import java.util.Scanner;

import customExceptions.ThereAreNoTurnsForAttendException;
import customExceptions.UserAlreadyExistException;
import customExceptions.UserAlreadyHasATurnException;
import customExceptions.UserNotFoundException;
import model.ControlSystem;
import model.Turn;
import model.User;
import java.lang.Exception;

public class Main {
	
	private ControlSystem cs= new ControlSystem();
	private Scanner reader= new Scanner(System.in);
	public Main() {
		
	}
	
	public static void main(String[] args) {
		Main main=new Main();
		
		int operation=0;
		boolean exit=false;
		
		do {
			main.showMenu();
			boolean a=false;
			do {
				try {
					operation=Integer.parseInt(main.reader.nextLine());
					if(operation>0 && operation<=7) {
						a=true;
					}else {
						System.out.println("you must give a number between 1 and 7");
					}
				}catch(NumberFormatException e) {
					System.out.println("the value given is not a number");
				}
			}while(!a);
			
			switch(operation) {
				case 1:
					try {
						main.firstOption();
					}catch(NumberFormatException e) {
						System.out.println("the value given is not a number");
					} catch(UserAlreadyExistException e) {
						System.out.println(e.getMessage());
					}catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					try {
						main.secondOption();
					}catch(NumberFormatException e) {
						System.out.println("the value given is not a number");
					}catch(UserNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					try {
						main.thirdOption();
					}catch(UserNotFoundException e) {
						System.out.println(e.getMessage());
					}catch(UserAlreadyHasATurnException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 4:
					try {
						main.fourthOption();
					}catch(ThereAreNoTurnsForAttendException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 5:
					try {
						main.fifthOption();
					}catch(ThereAreNoTurnsForAttendException e){
						System.out.println(e.getMessage());
					}
					break;
				case 6:
					main.sixthOption();
					break;
				case 7:
					exit=true;
					break;
			
			}	
			
		}while(!exit);

	}

	/**
	 * <b>Name:</b> showMenu.<br>
	 * This method shows the main menu.<br>
	*/
	public void showMenu() {
		System.out.println("please enter the operation you want to use");
		System.out.println("1: add a new user \n2: look for a user \n3: assign a turn \n4: show the next turn \n5: attend a turn \n6: reset the turns \n7: exit");
	}
	
	/**
	 * <b>Name:</b> firstOption.<br>
	 * This method gets the values of the information for the new user and calls the method addNewUser to create the user.<br>
	 * @throws NumberFormatException.<br>
	 * @throws Exception.<br>
	 * @throws UserAlreadyExistException.<br>
	*/
	public void firstOption() throws NumberFormatException ,Exception,UserAlreadyExistException{
		int type=0;
		String typeId="";
		String id;
		String firstNames;
		String lastNames;
		String address; 
		String telephone;
		boolean a=false;
		
		do {
			System.out.println("please enter your type of ID: \nEnter 1 for targeta de identidad \nEnter 2 for cedula de ciudadania \nEnter 3 for registro civil \nEnter 4 for pasaporte \nEnter 5 for cadula de ciudadania extrangera");
			type=Integer.parseInt(reader.nextLine());
			if(type>0 && type<=5) {
				a=true;
				switch(type) {
					case 1:
						typeId=User.TI;
						break;
					case 2:
						typeId=User.CC;
						break;
					case 3:
						typeId=User.RC;
						break;
					case 4:
						typeId=User.PASSPORT;
						break;
					case 5:
						typeId=User.FOREIGN_IDENTITY_CARD;
						break;
				}
			}else {
				System.out.println("you must give a number between 1 and 5");
			}
		}while(!a);
		
		System.out.println("please enter the number of ID");
		id=reader.nextLine();
		Long.parseLong(id);
		
		System.out.println("please enter the first names");
		firstNames=reader.nextLine();
		for(int i=0;i<firstNames.length();i++) {
			if(!Character.isLetter(firstNames.charAt(i)) && !(firstNames.charAt(i)==' ')) {
				throw new Exception("the firt names can't contain numbers");
			}
		}
		
		System.out.println("please enter the last names");
		lastNames=reader.nextLine();
		for(int i=0;i<firstNames.length();i++) {
			if(!Character.isLetter(lastNames.charAt(i)) && !(lastNames.charAt(i)==' ')) {
				throw new Exception("the last names can't contain numbers");
			}

		}
		
		System.out.println("please enter the address (you can leave this place in blank)");
		address=reader.nextLine();
		
		System.out.println("please enter the telephone (you can leave this place in blank)");
		telephone=reader.nextLine();
		Long.parseLong(id);
		
		cs.addNewUser(typeId, id, firstNames, lastNames, address, telephone);
	}
	
	/**
	 * <b>Name:</b> secondOption.<br>
	 * This method gets the information to look for the user and calls the method searchUser.<br>
	 * @throws UserNotFoundException.<br>
	*/
	public void secondOption() throws UserNotFoundException {
		int type=0;
		String typeId="";
		String id;
		boolean a=false;
		
		do {
			System.out.println("please enter your type of ID: \nEnter 1 for targeta de identidad \nEnter 2 for cedula de ciudadania \nEnter 3 for registro civil \nEnter 4 for pasaporte \nEnter 5 for cadula de ciudadania extrangera");
			type=Integer.parseInt(reader.nextLine());
			if(type>0 && type<=5) {
				a=true;
				switch(type) {
					case 1:
						typeId=User.TI;
						break;
					case 2:
						typeId=User.CC;
						break;
					case 3:
						typeId=User.RC;
						break;
					case 4:
						typeId=User.PASSPORT;
						break;
					case 5:
						typeId=User.FOREIGN_IDENTITY_CARD;
						break;
				}
			}else {
				System.out.println("you must give a number between 1 and 5");
			}
		}while(!a);
		
		System.out.println("please enter the number of ID");
		id=reader.nextLine();
		Long.parseLong(id);

		System.out.println(cs.searchUser(id, typeId));
	}
	
	/**
	 * <b>Name:</b> secondOption.<br>
	 * This method gets the information to assign a turn and calls the method assignTurn.<br>
	 * @throws UserNotFoundException.<br>
	 * @throws UserAlreadyHasATurnException.<br>
	*/
	public void thirdOption() throws UserNotFoundException, UserAlreadyHasATurnException {
		int type=0;
		String typeId="";
		String id;
		boolean a=false;
		
		do {
			System.out.println("please enter your type of ID: \nEnter 1 for targeta de identidad \nEnter 2 for cedula de ciudadania \nEnter 3 for registro civil \nEnter 4 for pasaporte \nEnter 5 for cadula de ciudadania extrangera");
			type=Integer.parseInt(reader.nextLine());
			if(type>0 && type<=5) {
				a=true;
				switch(type) {
					case 1:
						typeId=User.TI;
						break;
					case 2:
						typeId=User.CC;
						break;
					case 3:
						typeId=User.RC;
						break;
					case 4:
						typeId=User.PASSPORT;
						break;
					case 5:
						typeId=User.FOREIGN_IDENTITY_CARD;
						break;
				}
			}else {
				System.out.println("you must give a number between 1 and 5");
			}
		}while(!a);
		
		System.out.println("please enter the number of ID");
		id=reader.nextLine();
		Long.parseLong(id);
		
		System.out.println(cs.assignTurn(id, typeId));
	}
	
	/**
	 * <b>Name:</b> secondOption.<br>
	 * This method calls the method showNextTurn.<br>
	 * @throws ThereAreNoTurnsForAttendException.<br>
	*/
	public void fourthOption() throws ThereAreNoTurnsForAttendException {
		System.out.println(cs.showNextTurn());
	}
	
	/**
	 * <b>Name:</b> secondOption.<br>
	 * This method gets the information to attend a turn and calls the method attendTurn.<br>
	 * @throws ThereAreNoTurnsForAttendException.<br>
	*/
	public void fifthOption() throws ThereAreNoTurnsForAttendException {
		String status="";
		int stat=0;
		boolean a=false;
		
		do {
			System.out.println("please enter the user status: \nEnter 1 for attended \nEnter 2 for user was not");
			stat=Integer.parseInt(reader.nextLine());
			if(stat>0 && stat<=2) {
				a=true;
				switch(stat) {
					case 1:
						status=Turn.ATTENDED;
						break;
					case 2:
						status=Turn.USER_WAS_NOT;
						break;

				}
			}else {
				System.out.println("you must give a number between 1 and 2");
			}
		}while(!a);
		
		cs.attendTurn(status);
	}
	
	/**
	 * <b>Name:</b> secondOption.<br>
	 * This method calls the method resetTurns.<br>
	*/
	public void sixthOption() {
		cs.resetTurns();
	}
}
