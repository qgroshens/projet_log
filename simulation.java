package projet_log;


public class simulation implements Runnable {

	private boolean attente = false;

	public static void main (String[] args){
		Fond fenetre_reglage = new Fond("Entrez vos donnez ci-dessous");
		Thread temps = new Thread();
		temps.start();
		route route=new route(6,60,20,100,0.2,0.00,0.0000,0.8);//int vmax, int longueur,int nb_voiture, int nb_itt, double p,double p2
		route.creation();
		route.init_matrice_densite(60, 300);
		Affichage affichage=new Affichage();
		Fond fenetre = new Fond(route,affichage);


	}

	public void set_attente(boolean b){
		attente = b;
	}

	public void run() {
		while(attente){
			
		}
	}
	

	/*for(int i=0;i<200;i++){
		route.step();
	}
	route.ecrireDensiteText();
	route.ecrireVoitureText();*/
	//System.out.println("debit : "+route.getDebit());

	/*
	double debit=0;
	int moyenne=1;
	for(int i=0;i<moyenne;i++){
		route route=new route(5,20,5,20,0);//int vmax, int longueur,int nb_voiture, int nb_itt, double p
		route.creation();
		debit=debit+route.simulation();

	}
	System.out.println("debit : "+debit/moyenne);
	 */
}
