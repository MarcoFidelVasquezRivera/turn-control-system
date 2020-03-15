package model;

import java.util.ArrayList;
import java.util.Arrays;
import customExceptions.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

@SuppressWarnings("serial")
public class ControlSystem implements Serializable{
//+++++++++++++++++++++++++++++++++++
//	 ATTRIBUTES
//+++++++++++++++++++++++++++++++++++
	public static final String[] ADDRESS_WORD= {"carrera","calle","transversal","avenida"};
	public static final String NAMES_PATH="files"+File.separator+"names.txt";
	public static final String LASTNAMES_PATH="files"+File.separator+"lastName.txt";
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Turn> turns = new ArrayList<Turn>();
	private ArrayList<Turn> turnsAttended = new ArrayList<Turn>();
	private ArrayList<TurnType> turnType = new ArrayList<TurnType>();
	private DateTime dateTimeDiff = new DateTime();
	private DateTime lastAttended = new DateTime();
	private DateTime current = new DateTime();
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
	public String assignTurn(String id, String typeId,TurnType tp) throws UserNotFoundException, UserAlreadyHasATurnException {
		searchUser(id,typeId);
		String message="";
		boolean flag=false;
		
		for(int i=0;i<users.size() && !flag;i++) {
	
			if(users.get(i).getId().equalsIgnoreCase(id) && users.get(i).getTypeId().equalsIgnoreCase(typeId) && users.get(i).getActiveTurn()!=false) {
				throw new UserAlreadyHasATurnException(id,typeId,users.get(i).getFirstNames(),users.get(i).getTurn().getNumber());			
			}else if(users.get(i).getId().equalsIgnoreCase(id) && users.get(i).getTypeId().equalsIgnoreCase(typeId) && users.get(i).getActiveTurn()==false) {	
				Calendar ca = new GregorianCalendar();
				
				Calendar userDate = new GregorianCalendar();
				userDate.add(Calendar.YEAR, (-Calendar.YEAR+dateTimeDiff.getYears()));
				userDate.add(Calendar.MONTH, (-Calendar.MONTH+dateTimeDiff.getMonths()));
				userDate.add(Calendar.DAY_OF_MONTH, (-Calendar.DAY_OF_MONTH+dateTimeDiff.getDays()));
				userDate.add(Calendar.HOUR_OF_DAY, (-Calendar.HOUR_OF_DAY+dateTimeDiff.getHour()));
				userDate.add(Calendar.MINUTE, (-Calendar.MINUTE+dateTimeDiff.getMinutes()));
				userDate.add(Calendar.SECOND, (-Calendar.SECOND+dateTimeDiff.getSeconds()));
				
				if(ca.before(userDate)) {
					Turn newTurn = new Turn(String.valueOf(alphabet[letter])+Integer.toString(nTwo)+Integer.toString(nOne), users.get(i).getFirstNames()+users.get(i).getLastNames(), users.get(i).getId(), Turn.NOT_ATTENDED_YET,tp,typeId);
					users.get(i).setTurn(newTurn);
					turns.add(newTurn);
					message="the turn has been assigned: \n"+users.get(i).getTurn().getNumber();
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
		if(turns.isEmpty()) {
			throw new ThereAreNoTurnsForAttendException();
		}
		
		return turns.get(0).getNumber();
	}
	
	/**
	 * <b>Name:</b> attendTurn.<br>
	 * This method attends a turn.<br>
	 * <b>pre:</b> ArrayList users and ArrayList turnsAttended must be initialized.<br>
	 * @param  status the status of the user when they was called to attend them (attended or user was not). <br>
	 * @throws ThereAreNoTurnsForAttendException.<br> 
	 * @return void.<br>
	*/
	
	public void attendTurn() throws ThereAreNoTurnsForAttendException {
		String status="";
		Random r = new Random();
		int s = r.nextInt(2);
		
		switch(s) {
			case 1:
				status = Turn.ATTENDED;
				break;
			case 2:
				status = Turn.USER_WAS_NOT;
				break;
		}
		
		if(turns.isEmpty()) {
			throw new ThereAreNoTurnsForAttendException();
		}else {
			
			turnsAttended.add(turns.get(0));
			turnsAttended.get(turnsAttended.size()-1).setUserStatus(status);
			turns.remove(0);
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
		int nId=0;
		Random r= new Random();
		BufferedReader brNames = new BufferedReader(new FileReader(new File(NAMES_PATH)));
		BufferedReader brLastNames = new BufferedReader(new FileReader(new File(LASTNAMES_PATH)));
		
		String[] n = brNames.readLine().split(";");
		String[] ln = brLastNames.readLine().split(";");
		brNames.close();
		brLastNames.close();
		
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
	public void updateDate(int s, int m,int h,int d,int mo,int y) {
		Calendar calendar = new GregorianCalendar();
		int sd = s-dateTimeDiff.getSeconds()+calendar.get(Calendar.SECOND);
		int md = m-dateTimeDiff.getMinutes()+calendar.get(Calendar.MINUTE);
		int hd = h-dateTimeDiff.getHour()+calendar.get(Calendar.HOUR_OF_DAY);
		int dd = d-dateTimeDiff.getDays()+calendar.get(Calendar.DAY_OF_MONTH);
		int mod = mo-dateTimeDiff.getMonths()+calendar.get(Calendar.MONTH)+1;
		int yd = y-dateTimeDiff.getYears()+calendar.get(Calendar.YEAR);
		
		
		if(yd>0) {
			update(s, m, h, d, mo, y,calendar);
		}else if(yd==0 && mod>0){
			update(s, m, h, d, mo, y,calendar);
		}else if(yd==0 && mod==0 && dd>0) {
			update(s, m, h, d, mo, y,calendar);
		}else if(yd==0 && mod==0 && dd==0 && hd>0) {
			update(s, m, h, d, mo, y,calendar);
		}else if(yd==0 && mod==0 && dd==0 && hd==0 && md>0) {
			update(s, m, h, d, mo, y,calendar);
		}else if(yd==0 && mod==0 && dd==0 && hd==0 && md==0 && sd>0) {
			update(s, m, h, d, mo, y,calendar);
		}
	}
	
	public void update(int s, int m,int h,int d,int mo,int y, Calendar calendar) {
		dateTimeDiff.setSeconds(s+dateTimeDiff.getSeconds()-calendar.get(Calendar.SECOND));
		dateTimeDiff.setMinutes(m+dateTimeDiff.getMinutes()-calendar.get(Calendar.MINUTE));
		dateTimeDiff.setHour(h+dateTimeDiff.getHour()-calendar.get(Calendar.HOUR_OF_DAY));
		dateTimeDiff.setDays(d+dateTimeDiff.getDays()-calendar.get(Calendar.DAY_OF_MONTH));
		dateTimeDiff.setMonths(mo+dateTimeDiff.getMonths()-calendar.get(Calendar.MONTH)+1);
		dateTimeDiff.setYears(y+dateTimeDiff.getYears()-calendar.get(Calendar.YEAR));
	}
	
	public void updateDate() {
		Calendar calendar = new GregorianCalendar();
		current.setSeconds(calendar.get(Calendar.SECOND));
		current.setMinutes(calendar.get(Calendar.MINUTE));
		current.setHour(calendar.get(Calendar.HOUR_OF_DAY));
		current.setDays(calendar.get(Calendar.DAY_OF_MONTH));
		current.setMonths(calendar.get(Calendar.MONTH)+1);
		current.setYears(calendar.get(Calendar.YEAR));
	}
	
	//crear un metodo para atender turnos hasta la fecha
	public void attendTurnsUntillActualDate() throws ThereAreNoTurnsForAttendException {
		if(turns.isEmpty()) {
			throw new ThereAreNoTurnsForAttendException();
		}else {
			boolean attended = true;
			for(int i=0;i<turns.size() && attended;i++) {
				//Calendar calendar = new GregorianCalendar();
				int minutes = (int)turns.get(0).getTurnType().getMinutesDelay();
				int seconds = (int)((turns.get(0).getTurnType().getMinutesDelay()-minutes)*60);
				
				Calendar programDate = new GregorianCalendar();
				programDate.add(Calendar.YEAR, dateTimeDiff.getYears());
				programDate.add(Calendar.MONTH, dateTimeDiff.getMonths());
				programDate.add(Calendar.DAY_OF_MONTH, dateTimeDiff.getDays());
				programDate.add(Calendar.HOUR_OF_DAY, dateTimeDiff.getHour());
				programDate.add(Calendar.MINUTE, dateTimeDiff.getMinutes());
				programDate.add(Calendar.SECOND, dateTimeDiff.getSeconds());
				 
				Calendar la = new GregorianCalendar();
				la.add(Calendar.YEAR, -(Calendar.YEAR-lastAttended.getYears()));
				la.add(Calendar.MONTH, -(Calendar.MONTH-lastAttended.getMonths()));
				la.add(Calendar.DAY_OF_MONTH, -(Calendar.DAY_OF_MONTH-lastAttended.getDays()));
				la.add(Calendar.HOUR_OF_DAY, -(Calendar.HOUR_OF_DAY-dateTimeDiff.getHour()));
				la.add(Calendar.MINUTE, -(Calendar.MINUTE-dateTimeDiff.getMinutes()));
				la.add(Calendar.SECOND, -(Calendar.SECOND-dateTimeDiff.getSeconds()));
				
				la.add(la.get(Calendar.MINUTE), minutes);
				la.add(la.get(Calendar.SECOND), seconds+15);
				
				if(programDate.before(la)) {
					if(turns.isEmpty()) {
						lastAttended.setYears(programDate.get(Calendar.YEAR));
						lastAttended.setMonths(programDate.get(Calendar.MONTH));
						lastAttended.setDays(programDate.get(Calendar.DAY_OF_MONTH));
						lastAttended.setHour(programDate.get(Calendar.HOUR_OF_DAY));
						lastAttended.setMinutes(programDate.get(Calendar.MINUTE));
						lastAttended.setSeconds(programDate.get(Calendar.SECOND));
						attended=false;
					}else {
						attendTurn();
						lastAttended.setYears(la.get(Calendar.YEAR));
						lastAttended.setMonths(la.get(Calendar.MONTH));
						lastAttended.setDays(la.get(Calendar.DAY_OF_MONTH));
						lastAttended.setHour(la.get(Calendar.HOUR_OF_DAY));
						lastAttended.setMinutes(la.get(Calendar.MINUTE));
						lastAttended.setSeconds(la.get(Calendar.SECOND));
					}
				}else {
					attended=false;
					lastAttended.setYears(programDate.get(Calendar.YEAR));
					lastAttended.setMonths(programDate.get(Calendar.MONTH));
					lastAttended.setDays(programDate.get(Calendar.DAY_OF_MONTH));
					lastAttended.setHour(programDate.get(Calendar.HOUR_OF_DAY));
					lastAttended.setMinutes(programDate.get(Calendar.MINUTE));
					lastAttended.setSeconds(programDate.get(Calendar.SECOND));
				}
			}
		}
	}
	
	//crear un metodo para generar turnos al azar, poniendo el total de dias y el numero de turnos por dia
	public void generateRandomTurn() throws UserNotFoundException, UserAlreadyHasATurnException, ThereIsNotTurnTypeException {
		Random r = new Random();
		int user = r.nextInt(users.size());
		
		if(turnType.isEmpty()) {
			throw new ThereIsNotTurnTypeException();
		}
		String id = users.get(user).getId();
		String typeId = users.get(user).getTypeId();
		TurnType tp = turnType.get(r.nextInt(turnType.size()));
		

		assignTurn(id, typeId,tp);
		passTurn();
	}
	
	public String ReportTurnsPerson(String id, String typeId) throws UserNotFoundException {
		String report="";
		searchUser(id,typeId);
		ArrayList<Turn> t = new ArrayList<Turn> ();
		boolean flag = false;
		
		for(int i=0;i<users.size() && !flag;i++) {
			if(users.get(i).getId().equalsIgnoreCase(id) && users.get(i).getTypeId().equalsIgnoreCase(typeId)) {
				t = users.get(i).getArrayTurn();
				flag=true;
			}
		}
		
		for(int i=0;i<t.size();i++) {
			String attended;
			String status;
			if(t.get(i).getUserStatus().equalsIgnoreCase(Turn.NOT_ATTENDED_YET)) {
				attended = Turn.NOT_ATTENDED_YET;
			}else {
				attended = "attended";
			}
			
			if(attended.equalsIgnoreCase("attended")) {
				status = t.get(i).getUserStatus();
			}else {
				status = "xxxxxxxxxxxxxxxxxxxx";
			}
			report = report+"turn's number: "+t.get(i).getNumber()+"----"+attended+"----"+status+"\n";
			report = report+"----------------------------------------------- \n";
			// turno, si ya fue atendido y si la persona estba presente cuando fue llamada
		}
		
		return report;
	}
	
	public String reportPeopleByTurn(String t) {
		String report = "";
		String attended = "";
		boolean flag = false;
		Collections.sort(turns);
		//comparator to sort attendedTurns
		Comparator<Turn> co = new Comparator<Turn>() {

			@Override
			public int compare(Turn o1, Turn o2) {
				String number1 = o1.getNumber();
				String number2 = o2.getNumber();
				
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
			
		};
		Collections.sort(turnsAttended,co);
		int index = turnBynarySearch(turns, t);
		
		for(int i=index;i<turns.size() && !flag;i++) {
			if(turns.get(i).getNumber().equalsIgnoreCase(t)) {
				if(turns.get(i).getUserStatus().equalsIgnoreCase(Turn.NOT_ATTENDED_YET)) {
					attended = Turn.NOT_ATTENDED_YET;
				}else {
					attended = "attended";
				}
				report = report+"Name: "+turns.get(i).getUserName()+"----"+"type of id: "+turns.get(i).getUserTypeId()+"----"+"id: "+turns.get(i).getUserId()+"----"+attended+"\n";
				report = report+"----------------------------------------------- \n";
			}else {
				flag=true;
			}
		}
		
		flag = false;
		for(int i=index-1;i>=0 && !flag;i--) {
			if(turns.get(i).getNumber().equalsIgnoreCase(t)) {
				if(turns.get(i).getUserStatus().equalsIgnoreCase(Turn.NOT_ATTENDED_YET)) {
					attended = Turn.NOT_ATTENDED_YET;
				}else {
					attended = "attended";
				}
				report = report+"Name: "+turns.get(i).getUserName()+"----"+"type of id: "+turns.get(i).getUserTypeId()+"----"+"id: "+turns.get(i).getUserId()+"----"+attended+"\n";
				report = report+"----------------------------------------------- \n";
			}else {
				flag=true;
			}
		}
		/*
		for(int i=0;i<turns.size();i++) {
			if(turns.get(i).getNumber().equalsIgnoreCase(t)) {
				if(turns.get(i).getUserStatus().equalsIgnoreCase(Turn.NOT_ATTENDED_YET)) {
					attended = Turn.NOT_ATTENDED_YET;
				}else {
					attended = "attended";
				}
				report = report+"Name: "+turns.get(i).getUserName()+"----"+"type of id: "+turns.get(i).getUserTypeId()+"----"+"id: "+turns.get(i).getUserId()+"----"+attended+"\n";
				report = report+"-----------------------------------------------";
			}
		}
		*/
		index = turnBynarySearch(turnsAttended, t);
		flag = false;
		for(int i=index;i<turns.size() && !flag;i++) {
			if(turnsAttended.get(i).getNumber().equalsIgnoreCase(t)) {
				if(turnsAttended.get(i).getUserStatus().equalsIgnoreCase(Turn.NOT_ATTENDED_YET)) {
					attended = Turn.NOT_ATTENDED_YET;
				}else {
					attended = "attended";
				}
				report = report+"Name: "+turnsAttended.get(i).getUserName()+"----"+"type of id: "+turnsAttended.get(i).getUserTypeId()+"----"+"id: "+turnsAttended.get(i).getUserId()+"----"+attended+"\n";
				report = report+"----------------------------------------------- \n";
			}else {
				flag=true;
			}
			
		}
		
		flag = false;
		for(int i=index-1;i>=0 && !flag;i--) {
			if(turnsAttended.get(i).getNumber().equalsIgnoreCase(t)) {
				if(turnsAttended.get(i).getUserStatus().equalsIgnoreCase(Turn.NOT_ATTENDED_YET)) {
					attended = Turn.NOT_ATTENDED_YET;
				}else {
					attended = "attended";
				}
				report = report+"Name: "+turnsAttended.get(i).getUserName()+"----"+"type of id: "+turnsAttended.get(i).getUserTypeId()+"----"+"id: "+turnsAttended.get(i).getUserId()+"----"+attended+"\n";
				report = report+"----------------------------------------------- \n";
			}else {
				flag=true;
			}
			
		}
		/*
		for(int i=0;i<turnsAttended.size();i++) {
			if(turnsAttended.get(i).getNumber().equalsIgnoreCase(t)) {
				if(turnsAttended.get(i).getUserStatus().equalsIgnoreCase(Turn.NOT_ATTENDED_YET)) {
					attended = Turn.NOT_ATTENDED_YET;
				}else {
					attended = "attended";
				}
				report = report+"Name: "+turnsAttended.get(i).getUserName()+"----"+"type of id: "+turnsAttended.get(i).getUserTypeId()+"----"+"id: "+turnsAttended.get(i).getUserId()+"----"+attended+"\n";
				report = report+"-----------------------------------------------";
			}
		}
		*/
		
		return report;
	}
	
	public int turnBynarySearch(ArrayList<Turn> t, String s) {
		int position = 0;
		int max = t.size()-1;
		int min = 0;
		int middle = (max+min)/2;
		
		while(min<=max) {
			if(t.get(middle).getNumber().equalsIgnoreCase(s)) {
				position=middle;
			}else if(t.get(middle).getNumber().compareTo(s)<0) {
				min = middle+1;
			}else {
				max = middle-1;
			}
			middle = (max+min)/2;
		}
		
		return position;
	}
	
	public void suspendUsers() {
		Comparator<User> co = new UsersFirstNameComparator();
		Collections.sort(users,co);
		
		for(int i=0;i<users.size();i++) {
			ArrayList<Turn> t = new ArrayList<Turn>();
			t = users.get(i).getArrayTurn();
			if(t.get(t.size()-1).getUserStatus().equalsIgnoreCase(Turn.USER_WAS_NOT) && t.get(t.size()-2).getUserStatus().equalsIgnoreCase(Turn.USER_WAS_NOT)) {
				Calendar ca = new GregorianCalendar();
				int s = ca.get(Calendar.SECOND);
				int m = ca.get(Calendar.MINUTE);
				int h = ca.get(Calendar.HOUR_OF_DAY);
				int d = ca.get(Calendar.DAY_OF_MONTH);
				int mo = ca.get(Calendar.MONTH);
				int y = ca.get(Calendar.YEAR);
				users.get(i).setDt(s, m, h, d, mo, y);
			}
		}
	}
	
	public String showTurnsType() {
		String message="";
		
		for(int i=0; i<turnType.size();i++) {
			message = message+"Name: "+turnType.get(i).getName()+"---"+"Duration: "+String.valueOf(turnType.get(i).getMinutesDelay())+"\n";
			message= message+"------------------------------ \n";
		}
		
		return message;
	}
	
}
