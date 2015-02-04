package projet_log;

public class route {


	final int vmax=3;
	final int longueur=10;
	final int nb_voiture=4;
	model model;
	int nb_itteration=20;
	final voiture[] liste_voit=new voiture[nb_voiture];
	int i=0;
	int position=longueur;
	final double p=0.2;
	int num=0;
	boolean[] route;


	public	void creation(){
		voiture voiture_devant=null;
		route=new boolean[longueur];
		do{
			num=num+1;
			position=position-2;
			liste_voit[i]=new voiture(position, voiture_devant,num,route);
			voiture_devant=liste_voit[i];
			i++;
		}while(i<nb_voiture);
		liste_voit[0].set_devant(liste_voit[nb_voiture-1]);
		model= new nagel(vmax,longueur,p);
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








private	void affichage(int longueur, boolean[] route){
		System.out.print("//");
		for(int k=0;k<longueur;k++){
			if(route[k]){
				System.out.print("*");
			}else{
				System.out.print("-");
			}

		}
		System.out.println("//");
	}

}


















