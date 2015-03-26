package projet_log;

import java.util.concurrent.Semaphore;




public class ThreadSimu extends Thread {

	private Semaphore lancement;

	public ThreadSimu(Semaphore sema) {
		// TODO Auto-generated constructor stub
		lancement=sema;
	}


	public void run() {
		Fond fenetre_reglage = new Fond("Entrez vos donnez ci-dessous",lancement);
		try{ lancement.acquire();

		}
		catch(InterruptedException e){
		}
		//attente = !fenetre_reglage.get_gomain();
		Parametrage param =fenetre_reglage.get_Parametrage();
	
		route route=new route(param.get_vitesse_max(),param.get_taille_route(),param.get_nbvoiture(),param.get_nbstep(),param.get_proba_ralentir(),0.00,0.0000,0.8);//int vmax, int longueur,int nb_voiture, int nb_itt, double p,double p2
		route.creation();
		route.init_matrice_densite(60, 300);
		Affichage affichage=new Affichage();
		Fond fenetre = new Fond(route,affichage);
	}

}
