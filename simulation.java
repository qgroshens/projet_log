package projet_log;

public class simulation {
	public static void main (String[] args){
		double debit=0;
		int moyenne=1;
		for(int i=0;i<moyenne;i++){
			route route=new route(5,20,5,100,0);//int vmax, int longueur,int nb_voiture, int nb_itt, double p
			route.creation();
			debit=debit+route.simulation();

		}
		System.out.println("debit : "+debit/moyenne);
	}
}
