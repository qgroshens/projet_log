package projet_log;

public class voiture {
	private int vitesse;
	private int position;
	private voiture devant;
	private int num;
	private int[] route;

	voiture(int position, voiture voit, int numero,int[] route,int vmax) {
		vitesse=(int)(Math.floor(Math.random()*(vmax)));//vitesse aleatoire au départ
		num=numero;
		this.route=route;
		set_position(position);
		set_devant(voit);
	}

	void set_vitesse(int vit){
		vitesse=vit;
	}
	int get_vitesse(){
		return vitesse;
	}

	void set_devant(voiture voit){
		devant=voit;
	}

	voiture get_devant(){
		return devant;
	}

	int get_position(){
		return position;
	}
	void set_position(int pos){
		route[position]=0;
		position=pos;
		route[position]=num;
	}
	int get_num(){
		return num;
	}

}
