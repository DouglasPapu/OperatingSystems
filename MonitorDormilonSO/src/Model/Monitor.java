package Model;

import java.util.Queue;
import java.util.concurrent.Semaphore;

import Threads.MonitorThread;
import Threads.StudentThread;

/**
 * Monitor looks to available student  in chairs.
 * @author Douglas Lopez, Christian Cobo, Duvan Garcia
 *
 */
public class Monitor {
		
	private Corridor corridor;
	
	/**
	 * Attribute, serve to verify that a student is being attended.
	 */
	private StudentThread studentAttending;
	

	//SEMAPHORES IN JAVA
	
	private Semaphore semaphoreMonitor;
	
	private Semaphore semaphoreCorridor;
	
	private Semaphore semaphoreAttendCorridor;
	
	public Monitor() {
		studentAttending = null;
		semaphoreMonitor = new Semaphore(1);
		semaphoreCorridor = new Semaphore(corridor.SPACE_WAITING_ROOM);
		semaphoreAttendCorridor = new Semaphore(1);
		corridor = new Corridor();
	}
	
	
	/**
	 * Method that allows looks for the monitor to resolve doubts.
	 */
	public void searchMonitor(StudentThread student) {
			
		try {
			semaphoreMonitor.acquire();
			semaphoreCorridor.acquire();
			boolean attended = student.getState().equals(StudentThread.STATE_ATTENDED);
			
			if(!isBussy() && !attended) {
				
				System.out.println("Soy el estudiante id "+student.getId()+" empecé a ser atendido por el monitor");
				student.setState(StudentThread.STATE_WITH_MONITOR);
				studentAttending = student;
				
			}else if(corridor.isThereChairAvailable()) {
				corridor.assignStudentToWaitingRoom(student);
				System.out.println("Soy el estudiante id "+student.getId()+" el monitor está ocupado. Estoy en sala de espera.");
			}else {
				student.setState(StudentThread.STATE_IN_COMPUTERS);
				System.out.println("Soy el estudiante id "+student.getId()+" el monitor está monitor ocupado, no hay sillas. Volví a mi computador");
			}
					
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaphoreMonitor.release();
			semaphoreCorridor.release();			
		}

	}
	

	/**
	 * Get a student of the Waiting Room
	 * @return get a student of the waiting room
	 */
	public StudentThread getStudentFromWaitingRoom() {
		return corridor.getStudentWaitingRoom();
	}
	
	/**
	 * Assign student of Waiting Room to be attended Monitor
	 */
	public void getAttendStudentFromWaitingRoom() {
		 try {
			//semaphoreAttendCorridor.acquire();
			StudentThread studentPendent = corridor.getStudentWaitingRoom();
			System.out.println("El estudiante id " + studentPendent.getId() + " va ser atendido");
			studentAttending = studentPendent;
			Thread.sleep(3000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			 //semaphoreAttendCorridor.release();
		}
	}
	
	/**
	 * Verify if monitor is bussy
	 * @return
	 */
	public boolean isBussy() {
		return studentAttending != null ? true:false;
	}
	
	
	//METHODS GET AND SETTER

	public Corridor getCorridor() {
		return corridor;
	}



	public void setCorridor(Corridor corridor) {
		this.corridor = corridor;
	}


	public StudentThread getStudentAttending() {
		return studentAttending;
	}


	public void setStudentAttending(StudentThread studentAttending) {
		this.studentAttending = studentAttending;
	}


}
