package projet_log;

public class route {


	private int vmax;
	private int longueur;
	private int nb_voiture;
	private model model;
	private int nb_itteration;
	private voiture[] liste_voit;
	private int i=0;
	private int position;
	private double p;
	private int num=0;
	private int[] route;
	private voiture voiture_devant;

	route(int vmax, int longueur,int nb_voiture, int nb_itt, double p){
		this.vmax=vmax;
		this.longueur=longueur;
		this.nb_voiture=nb_voiture;
		this.nb_itteration=nb_itt;
		liste_voit=new voiture[nb_voiture];
		position=longueur;
		route=new int[longueur];
		voiture_devant=null;
		model= new nagel(vmax,longueur,p);
	}

	public	void creation(){
		do{
			num=num+1;
			position=position-2;
			liste_voit[i]=new voiture(position, voiture_devant,num,route);
			voiture_devant=liste_voit[i];
			i++;
		}while(i<nb_voiture);
		liste_voit[0].set_devant(liste_voit[nb_voiture-1]);
		i=0;
		affichage(longueur,route);
		System.out.println("fin création");
	}
	public void simulation(){

		do{
			System.out.println("tour restant "+nb_itteration);
			for(i=0;i<nb_voiture;i++){
				model.maj_vitesse(liste_voit[i]);
				model.maj_position(liste_voit[i]);
			}
			affichage(longueur,route);
			nb_itteration--;	
		}while(nb_itteration>0);
		System.out.println("fin simulation");
	}








private	void affichage(int longueur, int[] route){
		System.out.print("//");
		for(int k=0;k<longueur;k++){
			if(route[k]==0){
				System.out.print("-");
			}else{
				System.out.print(route[k]);
			}

		}
		System.out.println("//");
	}

}


















