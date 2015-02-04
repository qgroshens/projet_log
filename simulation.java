package projet_log;

public class simulation {
	public static void main (String[] args){
 
		route route=new route(3,10,4,20,0);//int vmax, int longueur,int nb_voiture, int nb_itt, double p
		route.creation();
		route.simulation();
		
			
		}
}
