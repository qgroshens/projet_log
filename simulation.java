package projet_log;



public class simulation {

	public static void main (String[] args){
		route route=new route(5,60,15,100,0.2,0.01,0.0001);//int vmax, int longueur,int nb_voiture, int nb_itt, double p,double p2
		route.creation();
		route.init_matrice_densite(60, 300);
		Affichage affichage=new Affichage();
		Fond fenetre = new Fond(route,affichage);
		Fond fenetre_reglage = new Fond("Entrez vos donnez ci-dessous",fenetre);
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
}
