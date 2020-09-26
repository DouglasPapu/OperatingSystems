package Model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import Threads.StudentThread;

/**
 * Class allow to assign student to waiting room.
 * 
 * @author Douglas Lopez, Christian Cobo, Duvan Garcia
 *
 */
public class Corridor {

	//Amount of chairs
	public static final int SPACE_WAITING_ROOM = 3;

	//Chairs of the Corridor
	private Queue<StudentThread> corridor;


	public Corridor() {
		corridor = new LinkedList<StudentThread>();
	}

	/**
	 * Method that assign a student only if exist a space at corridor.
	 * 
	 * @param student
	 */
	public void assignStudentToWaitingRoom(StudentThread student) {

		try {	
			if (isThereChairAvailable()) {		
				corridor.add(student);
				student.setState(StudentThread.STATE_IS_WAITING);
			} else {
				student.setState(StudentThread.STATE_IN_COMPUTERS);
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * Method verify if corridor is empty.
	 * 
	 * @return true if chairs are empty.
	 */
	public boolean isEmptyCorridor() {
		return corridor.size() == 0;
	}

	/**
	 * Methods verify if exist some chair available.
	 * 
	 * @return true if there is a available chair, in other case false.
	 */
	public boolean isThereChairAvailable() {
		return (corridor.size() < SPACE_WAITING_ROOM) ? true : false;
	}
	
	/**
	 * Get a student of the Waiting Room
	 * @return Student on state of wait. FIFO
	 */
	public StudentThread getStudentWaitingRoom() {

		return corridor.poll();
	}

}
