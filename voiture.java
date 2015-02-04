package projet_log;

public class voiture {
	private int vitesse;
	private int position;
	private voiture devant;
	private int num;
	private boolean[] route;

	voiture(int position, voiture voit, int numero, boolean[] route) {
		vitesse=0;
		num=numero;
		this.route=route;
		set_position(position);
		set_devant(voit);
	}

	void set_vitesse(int vit){
		vitesse=vit;
		//System.out.println("voiture n°"+num+" vitesse "+vit);
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
		route[position]=false;
		position=pos;
		route[position]=true;
		//System.out.println("voiture n°"+num+" position "+pos);
	}
	int get_num(){
		return num;
	}


}
