package projet_log;

public class nagel extends model {
	int vmax;
	int longueur;
	double p;//probas de ralentir
	double p2;//probas de ne pas redemarer
	double p3;//probas de stopper net
	int gap;
	int v;
	int position;
	int devant;

	nagel(int longueur, double p,double p2,double p3){
		this.p=p;
		this.p2=p2;
		this.p3=p3;
		this.longueur=longueur;
	}

	void maj_vitesse(voiture voit,int vmax){
		v=voit.get_vitesse();
		position=voit.get_position();
		devant=voit.get_devant().get_position();

		if(devant<position){
			gap=longueur-position+devant-1;
		}else{
			gap=devant-position-1;
		}
		if((Math.random() <= p3)){
			v=0;
		}else{
			if((v==0)&& (Math.random() <= p2)){
				//reste a zero
			}else{
				if(v<gap){
					v=Math.min(v+1,vmax);

				}else{
					v=Math.min(gap,vmax);
				}
				if ((v > 0) && (Math.random() <= p)) {
					v--;
				}
			}
		}
		voit.set_vitesse(v);


	}
	boolean maj_position(voiture voit){
		int pos = voit.get_position();
		boolean res=false;
		if(pos+voit.get_vitesse()>=longueur){
			res=true;
		}
		pos=(pos+voit.get_vitesse())%(longueur);
		if(pos<0)
		{pos=longueur+pos;}
		voit.set_position(pos);
		return res;
	}

}
