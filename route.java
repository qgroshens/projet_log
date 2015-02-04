package projet_log;

import java.util.Arrays;
public class route {


	private int longueur;
	private int nb_voiture;
	private model model;
	private int nb_itteration;
	private voiture[] liste_voit;
	private int i=0;
	private int[] position;
	private int num=0;
	private int[] route;
	private voiture voiture_devant;
	private int ecart;
	private int pos_local;
	private int vmax;

	route(int vmax, int longueur,int nb_voiture, int nb_itt, double p){
		this.vmax=vmax;
		this.longueur=longueur;
		this.nb_voiture=nb_voiture;
		this.nb_itteration=nb_itt;
		liste_voit=new voiture[nb_voiture];
		route=new int[longueur];
		voiture_devant=null;
		model= new nagel(vmax,longueur,p);
		position=new int[nb_voiture];
	}

	public	void creation(){
		gen_position(nb_voiture,longueur);
		do{
			num=num+1;
			liste_voit[i]=new voiture(position[i], voiture_devant,num,route,vmax);
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
		boolean[] occupation=new boolean[longueur];
		position[0]=(int)(Math.floor(Math.random()*(longueur)));//position premiere voiture
		
		for(int i=0;i<longueur;i++){
			occupation[i]=false;
		}
		occupation[position[0]]=true;
		for(int n=0;n<nb_voiture-1;n++){

			do{
				ecart=(int)(Math.floor(Math.random()*5));
				pos_local=(position[n]-ecart)%longueur;
				if(pos_local<0){
					pos_local=longueur+pos_local;
				}

			}while(occupation[pos_local]);
			position[n+1]=pos_local;
			occupation[pos_local]=true;
			
		}

		position=range(position);
		/*for(int n=0;n<position.length;n++){
		System.out.print(position[n]+";");
		}
		System.out.println("");
		*/
	}






	private	void affichage(int longueur, int[] route){
		System.out.print("//:");
		for(int k=0;k<longueur;k++){
			if(route[k]==0){
				System.out.print("-:");
			}else{
				System.out.print(""+route[k]+":");
			}

		}
		System.out.println("//");
	}
	private int[] range(int[] tableau){
		for(int i=0;i<tableau.length;i++){
			tableau[i]=-tableau[i];
		}
		Arrays.sort(tableau);
		for(int i=0;i<tableau.length;i++){
			tableau[i]=-tableau[i];
		}
		return tableau;
	}

}


















