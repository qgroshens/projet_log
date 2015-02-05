package projet_log;

public class simulation {
	public static void main (String[] args){
		double debit=0;
		for(int i=0;i<5;i++){
		route route=new route(5,100,99,100,0);//int vmax, int longueur,int nb_voiture, int nb_itt, double p
		route.creation();
		debit=debit+route.simulation();
		
		}
		System.out.println("debit : "+debit/5);
		}
}
