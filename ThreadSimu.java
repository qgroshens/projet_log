package projet_log;

import java.util.concurrent.Semaphore;


public class ThreadSimu extends Thread {

	private Semaphore lancement;

	public ThreadSimu() {
		lancement=new Semaphore(0);
	}

	public void run() {
		Fond fenetre_reglage = new Fond("Entrez vos donnez ci-dessous",lancement);
		try{ lancement.acquire();
		//attente du parametrage
		}
		catch(InterruptedException e){
		}
		//attente = !fenetre_reglage.get_gomain();
		Parametrage param =fenetre_reglage.get_Parametrage();

		route route=new route(param.get_vitesse_max(),param.get_taille_route(),param.get_nbvoiture(),param.get_nbstep(),param.get_proba_ralentir(),param.get_proba_demarrage(),param.get_proba_lapin(),param.get_seuil_regulation());//int vmax, int longueur,int nb_voiture, int nb_itt, double p,double p2
		route.creation();
		route.init_matrice_densite(param.get_taille_route(), param.get_nbstep());
		Affichage affichage=new Affichage();
		new Fond(route,affichage);
	}

}
