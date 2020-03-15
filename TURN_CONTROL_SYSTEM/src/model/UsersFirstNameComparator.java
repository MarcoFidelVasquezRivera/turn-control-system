package model;

import java.util.Comparator;

public class UsersFirstNameComparator implements Comparator<User>{

	@Override
	public int compare(User o1, User o2) {
		String name1 = o1.getFirstNames();
		String name2 = o2.getFirstNames();
		
		int comparation;
		
		if(name1.compareTo(name2)<0) {
			comparation = -1;
		}else if(name1.compareTo(name2)>0) {
			comparation = 1;
		}else {
			comparation=0;
		}
		
		return comparation;
	}

}
