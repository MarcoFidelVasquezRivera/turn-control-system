package ui;

import java.util.Calendar;
import java.util.IllegalFormatException;
import java.util.Scanner;
import customExceptions.ThereAreNoTurnsForAttendException;
import customExceptions.ThereIsNotTurnTypeException;
import customExceptions.UserAlreadyExistException;
import customExceptions.UserAlreadyHasATurnException;
import customExceptions.UserNotFoundException;
import model.ControlSystem;
import model.Turn;
import model.TurnType;
import model.User;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Exception;
import java.io.*;

public class Main {
	
	private ControlSystem cs= new ControlSystem();
	private Scanner reader= new Scanner(System.in);
	public Main() {
		
	}
	
	public static void main(String[] args) {
		Main main=new Main();
		ObjectInputStream bw;
		try {
			bw = new ObjectInputStream(new FileInputStream("data/programData.m"));
			main=(Main)bw.readObject();
			bw.close();
		} catch (IOException | ClassNotFoundException e1) {
		
		}
		
		
		int operation=0;
		boolean exit=false;
		
		do {
			main.showMenu();
			boolean a=false;
			do {
				try {
					operation=Integer.parseInt(main.reader.nextLine());
					if(operation>0 && operation<=14) {
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
					} catch (ThereIsNotTurnTypeException e) {
						// TODO Auto-generated catch block
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
				} catch (ThereAreNoTurnsForAttendException e) {
					System.out.println(e.getMessage());
				}
					
					break;
				case 6:
					main.sixthOption();
					break;
				case 7:
					main.seventhOption();
					break;
				case 8:
					try {
						main.eighthOption();
					} catch (IOException | UserAlreadyExistException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 9:
					try {
						main.ninthOption();
					} catch (UserNotFoundException | UserAlreadyHasATurnException | ThereIsNotTurnTypeException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 10:
					try {
						main.tenthOption();
					} catch (UserNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 11:
					main.eleventhOption();
					break;
				case 12:
					try {
						main.twelfthOption();
					} catch (ThereAreNoTurnsForAttendException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 13:
					try {
						main.thirteenthOption();
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 14:
					exit=true;
					try {
						bw = new ObjectInputStream(new FileInputStream("data/programData.m"));
						main=(Main)bw.readObject();
						bw.close();
					} catch (IOException | ClassNotFoundException e1) {
					
					}
					break;
			
			}	
			System.out.println(main.cs.showTime());
		}while(!exit);

	}

	/**
	 * <b>Name:</b> showMenu.<br>
	 * This method shows the main menu.<br>
	*/
	public void showMenu() {
		System.out.println("please enter the operation you want to use");
		System.out.println("1: add a new user "
				+ "\n2: look for a user "
				+ "\n3: assign a turn "
				+ "\n4: show the next turn "
				+ "\n5: update Time manualy"
				+ "\n6: update Time automaticaly"
				+ "\n7: reset the turns "
				+ "\n8: generate Random users"
				+ "\n9: generate Random Tunrs"
				+ "\n10: look for all the turns that a user has had"
				+ "\n11: look for all the user that has had a given turn"
				+ "\n12: attend turns until actual date"
				+ "\n13: save program data"
				+ "\n14: exit");
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
			System.out.println("please enter your type of ID: "
					+ "\nEnter 1 for targeta de identidad "
					+ "\nEnter 2 for cedula de ciudadania "
					+ "\nEnter 3 for registro civil "
					+ "\nEnter 4 for pasaporte "
					+ "\nEnter 5 for cadula de ciudadania extrangera");
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
		
		long  t1 = System.currentTimeMillis();
		cs.addNewUser(typeId, id, firstNames, lastNames, address, telephone);
		long  t2 = System.currentTimeMillis(); 
		System.out.println(t2-t1);
		
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

		long  t1 = System.currentTimeMillis();
		System.out.println(cs.searchUser(id, typeId));
		long  t2 = System.currentTimeMillis(); 
		System.out.println(t2-t1);
		
	}
	
	/**
	 * <b>Name:</b> secondOption.<br>
	 * This method gets the information to assign a turn and calls the method assignTurn.<br>
	 * @throws UserNotFoundException.<br>
	 * @throws UserAlreadyHasATurnException.<br>
	*/
	public void thirdOption() throws UserNotFoundException, UserAlreadyHasATurnException,ThereIsNotTurnTypeException {
		int type=0;
		String typeId="";
		String id;
		boolean a=false;
		int type2=0;
		
		int ttSize = cs.getTurnsType().size();
		
		if(ttSize==0) {
			throw new ThereIsNotTurnTypeException();
		}else {
			System.out.println("choose a type of turn: ");
			System.out.println(cs.showTurnsType());
		}
		
		TurnType tt=null;
		
		boolean flag=true;
		do {
			type2=Integer.parseInt(reader.nextLine());
			if(type2>0 && type2<cs.getTurnsType().size()) {
				
				tt =cs.getTurnsType().get(type2);
				flag=true;
				
			}else {
				System.out.println("you must give a number between 1 and 5");
			}
			
		}while(!flag);
		
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
		
		
		long  t1 = System.currentTimeMillis();
		System.out.println(cs.assignTurn(id, typeId,tt));
		long  t2 = System.currentTimeMillis(); 
		System.out.println(t2-t1);
		
	}
	
	/**
	 * <b>Name:</b> secondOption.<br>
	 * This method calls the method showNextTurn.<br>
	 * @throws ThereAreNoTurnsForAttendException.<br>
	*/
	public void fourthOption() throws ThereAreNoTurnsForAttendException {
		long  t1 = System.currentTimeMillis();
		System.out.println(cs.showNextTurn());
		long  t2 = System.currentTimeMillis(); 
		System.out.println(t2-t1);
		
	}
	
	/**
	 * <b>Name:</b> secondOption.<br>
	 * This method gets the information to attend a turn and calls the method attendTurn.<br>
	 * @throws ThereAreNoTurnsForAttendException.<br>
	*/
	
	public void fifthOption() throws ThereAreNoTurnsForAttendException {
		System.out.println("please enter the second of the date");
		int seconds =Integer.parseInt(reader.nextLine());
		while(seconds>60 || seconds<0) {
			System.out.println("value given must be between 0 and 60");
			seconds =Integer.parseInt(reader.nextLine());
		}
		
		System.out.println("please enter the minute of the date");
		int minutes = Integer.parseInt(reader.nextLine());
		while(minutes>60 || minutes<0) {
			System.out.println("value given must be between 0 and 60");
			minutes = Integer.parseInt(reader.nextLine());
		}
		
		System.out.println("please enter the hour of the date");
		int hour = Integer.parseInt(reader.nextLine());
		while(hour>24 || hour<0) {
			System.out.println("value given must be between 0 and 24");
			hour = Integer.parseInt(reader.nextLine());
		}
		
		System.out.println("please enter the day of the date");
		int days = Integer.parseInt(reader.nextLine());
		while(days>31 || minutes<1) {
			System.out.println("value given must be between 1 and 31");
			days = Integer.parseInt(reader.nextLine());
		}
		
		System.out.println("please enter the month of the date");
		int month = Integer.parseInt(reader.nextLine());
		while(month>12 || month<1) {
			System.out.println("value given must be between 1 and 12");
			month = Integer.parseInt(reader.nextLine());
		}
		
		System.out.println("please enter the year of the date");
		int year = Integer.parseInt(reader.nextLine());
		
		long  t1 = System.currentTimeMillis(); 
		cs.updateDate(seconds, minutes, hour, days, month, year);
		long  t2 = System.currentTimeMillis(); 
		System.out.println(t2-t1);
		
	}
	
	public void sixthOption() {
		long  t1 = System.currentTimeMillis(); 
		cs.updateDate();
		long  t2 = System.currentTimeMillis(); 
		System.out.println(t2-t1);
	}
	
	/**
	 * <b>Name:</b> secondOption.<br>
	 * This method calls the method resetTurns.<br>
	*/
	public void seventhOption() {
		cs.resetTurns();
	}
	
	public void eighthOption() throws FileNotFoundException, IOException, UserAlreadyExistException {
		long  t1 = System.currentTimeMillis(); 
		cs.generateRandomUser();
		long  t2 = System.currentTimeMillis(); 
		System.out.println(t2-t1);
	}
	
	public void ninthOption() throws UserNotFoundException, UserAlreadyHasATurnException, ThereIsNotTurnTypeException {
		long  t1 = System.currentTimeMillis(); 
		cs.generateRandomTurn();
		long  t2 = System.currentTimeMillis(); 
		System.out.println(t2-t1);
	}
	
	public void tenthOption() throws UserNotFoundException {
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
		long  t1 = System.currentTimeMillis();
		cs.ReportTurnsPerson(id, typeId);
		long  t2 = System.currentTimeMillis(); 
		System.out.println(t2-t1);
	}
	
	public void eleventhOption() {
		cs.resetTurns();
		String turn="";
		System.out.println("please enter the turn");
		turn = reader.nextLine();
		
		long  t1 = System.currentTimeMillis();
		cs.reportPeopleByTurn(turn);
		long  t2 = System.currentTimeMillis(); 
		System.out.println(t2-t1);
		
	}
	
	public void twelfthOption() throws ThereAreNoTurnsForAttendException {
		long  t1 = System.currentTimeMillis();
		cs.attendTurnsUntillActualDate();
		long  t2 = System.currentTimeMillis(); 
		System.out.println(t2-t1);
	}
	
	public void thirteenthOption() throws FileNotFoundException, IOException {
		
		ObjectOutputStream bw = new ObjectOutputStream(new FileOutputStream("data/programData.m"));
		long  t1 = System.currentTimeMillis();
		bw.writeObject(cs);
		
		bw.close();
		long  t2 = System.currentTimeMillis(); 
		System.out.println(t2-t1);
	}
}
