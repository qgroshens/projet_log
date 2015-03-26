package projet_log;

import java.util.concurrent.Semaphore;


public class simulation  {

	public static void main (String[] args){

		Semaphore sema=new Semaphore(0);
		ThreadSimu simu = new ThreadSimu(sema);
		simu.start();

	}

}
