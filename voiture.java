package log_essai1;

public class voiture {
	private int vitesse;
	private int position;
	private voiture devant;
	private int num;

	voiture(int position, voiture voit, int numero) {
		vitesse=0;
		num=numero;
		set_position(position);
		set_devant(voit);
	}

	void set_vitesse(int vit){
		vitesse=vit;
		System.out.println("voiture n°"+num+" vitesse "+vit);
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
		position=pos;
		System.out.println("voiture n°"+num+" position "+pos);
	}
	int get_num(){
		return num;
	}


}
