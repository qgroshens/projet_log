package projet_log;

public class nagel extends model {
	int vmax;
	int longueur;
	double p;//probas de ralentir
	int gap;
	int v;
	int position;
	int devant;

	nagel(int vmax, int longueur, double p){
		this.vmax=vmax;
		this.p=p;
		this.longueur=longueur;
	}

	void maj_vitesse(voiture voit){
		v=voit.get_vitesse();
		position=voit.get_position();
		devant=voit.get_devant().get_position();
		
		if(devant<position){
			gap=longueur-position+devant-1;
		}else{
			gap=devant-position-1;
		}
		if(v<gap){
			v=Math.min(v+1,vmax);
			
		}else{
			v=gap;
		}
		if ((v > 0) && (Math.random() <= p)) {
			v--;
		}
		voit.set_vitesse(v);

	}
	void maj_position(voiture voit){
		int pos = voit.get_position();
		pos=(pos+voit.get_vitesse())%(longueur);
		voit.set_position(pos);
	}

}
