package log_essai1;

public class route {

	public static void main (String[] args){

		final int vmax=3;
		final int longueur=10;
		final int nb_voiture=4;
		model model;
		int nb_itteration=5;
		final voiture[] liste_voit=new voiture[nb_voiture];
		int i=0;
		int position=longueur;
		final double p=0.2;
		int num=0;

		voiture voiture_devant=null;
		do{
			num=num+1;
			position=position-2;
			liste_voit[i]=new voiture(position, voiture_devant,num);
			voiture_devant=liste_voit[i];
			i++;
		}while(i<nb_voiture);
		liste_voit[0].set_devant(liste_voit[nb_voiture-1]);
		model= new nagel(vmax,longueur,p);
		i=0;
		System.out.println("fin création");
		do{
			System.out.println("tour restant "+nb_itteration);
			for(i=0;i<nb_voiture;i++){
				model.maj_vitesse(liste_voit[i]);
				model.maj_position(liste_voit[i]);
			}


			nb_itteration--;	
		}while(nb_itteration>0);
		System.out.println("fin simulation");






	}

















}
