package Threads;

import Model.Monitor;

/**
 * Create many students that belong to computers room.
 * @author Douglas Lopez, Christian Cobo, Duvan Garcia
 *
 */
public class StudentThread implements Runnable{
	
	/**
	 * Identification of the student
	 */
	private int id;
	/**
	 * Represent to a student in a computer
	 */
	public static final String STATE_IN_COMPUTERS = "COMPUTERS";
	
	/**
	 * Represents to a student in waiting room
	 */
	public static final String STATE_IS_WAITING = "WAITING";
	
	/**
	 * Represents to student in monitor office.
	 */
	public static final String STATE_WITH_MONITOR = "ATTENDING";
	
	/**
	 * Represents to a student has already been attended.
	 */
	public static final String STATE_ATTENDED = "ATTENDED";
	
	/**
	 * Stores a state to know what student does. 
	 */
	public String state;
	
	/**
	 * Environment of the Monitor.
	 */
	public Monitor mon;
	
	/**
	 * Initialize a student into computers room, student is working in his personal computer.
	 */
	public StudentThread(Monitor mon, int id) {
		this.state = STATE_IN_COMPUTERS;
		this.mon = mon;
		this.id = id;
	}
	

	@Override
	public void run() {
		
		while(true && !this.state.equals(STATE_ATTENDED)) {
			
			try {
				
				Thread.sleep(4000);
				
				double posibility = Math.random();
				
				if(posibility < 0.4 && state.equals(STATE_IN_COMPUTERS)) {
					System.out.println("El estudiante id "+ id +" está buscando al monitor");
					mon.searchMonitor(this);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	//METHODS GET AND SETTER

	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public Monitor getMon() {
		return mon;
	}


	public void setMon(Monitor mon) {
		this.mon = mon;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	

}
