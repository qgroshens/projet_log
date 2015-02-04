package projet_log;

import java.util.Arrays;

public class route {


	private int vmax;
	private int longueur;
	private int nb_voiture;
	private model model;
	private int nb_itteration;
	private voiture[] liste_voit;
	private int i=0;
	private int[] position;
	private double p;
	private int num=0;
	private int[] route;
	private voiture voiture_devant;
	private int ecart;
	int pos_local;

	route(int vmax, int longueur,int nb_voiture, int nb_itt, double p){
		this.vmax=vmax;
		this.longueur=longueur;
		this.nb_voiture=nb_voiture;
		this.nb_itteration=nb_itt;
		liste_voit=new voiture[nb_voiture];
		route=new int[longueur];
		voiture_devant=null;
		model= new nagel(vmax,longueur,p);
	}

	public	void creation(){
		gen_position(nb_voiture,longueur);
		do{
			num=num+1;
			liste_voit[i]=new voiture((longueur-position[i])%longueur, voiture_devant,num,route);
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

	private void gen_position(int nb_voiture, int longueur){
		position=new int[nb_voiture];
		position[0]=(int)(Math.floor(Math.random()*(longueur)));//position premiere voiture
		for(int n=0;n<nb_voiture-1;n++){

			do{
				ecart=(int)(Math.floor(Math.random()*5));
				pos_local=position[n]-ecart;
				if(pos_local<0){
					pos_local=longueur+pos_local;
				}

			}while(route[pos_local]!=0);
			position[n+1]=(pos_local)%longueur;
			n++;
		}
		Arrays.sort(position);
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


















