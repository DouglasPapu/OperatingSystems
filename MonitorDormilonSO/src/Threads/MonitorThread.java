package Threads;

import Model.Monitor;
/**
 * Represents unique Monitor
 * @author Douglas Lopez, Christian Cobo, Duvan Garcia
 *
 */
public class MonitorThread implements Runnable {

	
	/**
	 * Attribute to know of the monitor office.
	 */
	private Monitor mon;
	
	/**
	 * Constructor method
	 * @param moni
	 */
	public MonitorThread(Monitor moni) {
		mon = moni;
	}

	@Override
	public void run() {
			
		while(true) {
		try {
			Thread.sleep(1000);
			
			if(mon.isBussy()) {

					System.out.println("El monitor ha terminado de atender");
					
					if(!mon.getCorridor().isEmptyCorridor()) {
						System.out.println("Siguiente estudiante de Sala de Espera...");
						mon.getAttendStudentFromWaitingRoom();
						
					}else {
						System.out.println("No hay más para atender... Monitor se durmió");
						mon.setStudentAttending(null);
						Thread.sleep(3000);
					}
			   }
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	//METHOS GET AND SETTER

	public Monitor getMon() {
		return mon;
	}

	public void setMon(Monitor mon) {
		this.mon = mon;
	}
	
	

}
