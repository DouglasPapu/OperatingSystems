package Application;

import Model.Monitor;
import Threads.*;

/**
 * Execution Class 
 * @author Douglas Lopez, Christian Cobo, Duvan Garcia
 *
 */
public class Application {

	public static void main(String[] args) {
		

		//Initialize office monitor
		Monitor office = new Monitor();
		
		//Initialize random between 1 and 30 students.
		int amountStudents = (int) (Math.random()*30+1);
		
		System.out.println("Cantidad de estudiantes "+ amountStudents);
		
		try {
			Thread.sleep(3000);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < amountStudents; i++) {
			StudentThread st = new StudentThread(office, (int)(Math.random()*1000) + 1);
			Thread threadStudent = new Thread(st);
			threadStudent.start();
		}
		
		try {	
			Thread.sleep(3000);
		}catch(Exception e) {
			
		}
	
		
		MonitorThread mt = new MonitorThread(office);
		Thread threadMonitor = new Thread(mt);		
		threadMonitor.start();		

	}

}
