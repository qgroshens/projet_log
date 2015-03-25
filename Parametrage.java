package projet_log;

public class Parametrage {
	
	private int nombre_voiture;
	private int taille_route;
	private int vitesse_max;
	private double proba_ralentir;
	private double proba_demarrage;
	private double proba_lapin;
	private double seuil_regulation;
	private boolean regulation;


	public Parametrage(){

	}

	public void set_parametre (int nb_voit, int taille, int vit, double pral, double pdemar, double plapin, double seuil, boolean reg){
		nombre_voiture = nb_voit;
		taille_route = taille;
		vitesse_max = vit;
		proba_ralentir = pral;
		proba_demarrage = pdemar;
		proba_lapin = plapin;
		seuil_regulation = seuil;
		regulation = reg;
	}
	
	
	public int get_nbvoiture(){
		return nombre_voiture;
	}
	public int get_taille_route(){
		return taille_route;
	}
	public int get_vitesse_max(){
		return vitesse_max;
	}
	public double get_proba_ralentir(){
		return proba_ralentir;
	}
	public double get_proba_demarrage(){
		return proba_demarrage;
	}
	public double get_proba_lapin(){
		return proba_lapin;
	}
	public double get_seuil_regulation(){
		return seuil_regulation;
	}
	public boolean get_regulation(){
		return regulation;
	}
}


