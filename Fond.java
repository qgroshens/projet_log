package projet_log;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;

public class Fond extends JFrame  implements ActionListener{
	static final long serialVersionUID = 1;	

	private route route;
	private Boutons1 b_startstop;
	private Boutons1 b_increment;
	private Panneau fond;
	private boolean compteur = false;
	private Thread t;
	private int[] liste;



	public Fond(route route){

		this.setVisible(true);
		this.fond = new Panneau();
		this.setTitle("première interface");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.route=route;
		this.liste = route.get_route();
		
		
		//les boutons
		
		b_increment = new Boutons1("incrémente", this);
		b_startstop = new Boutons1("start/stop", this);
		fond.add(b_startstop);
		fond.add(b_increment);

		this.getContentPane().add(fond);

	}



	public void actionPerformed(ActionEvent evenement) {
		String bouton_appuye = evenement.getActionCommand();

		if(bouton_appuye == "incrémente"){
			route.step();
			fond.repaint();
		}
		/*else if (bouton_appuye == "dodo"){
			//rend le bouton cliquable ou non : mais ne marche pas avec thread.sleep()
			b.setEnabled(false);
			System.out.println("au lit");	
			b.setEnabled(true);
			System.out.println("debout");
		}*/
		else if (bouton_appuye == "start/stop"){

			if(compteur == false){
				b_increment.setEnabled(false);
				System.out.println("start");
				fond.set_bool_anim(true);
				this.t = new Thread(fond);
				fond.set_bool_anim(true);
				t.start();
				//fond.animation();
				compteur = true;

			}
			else
			{
				System.out.println("stop");
				
				fond.set_bool_anim(false);
				compteur = false;
				b_increment.setEnabled(true);
			}
		}

		else{
			System.out.println("un autre bouton svp");
		}

	}

	/*public void actionPerformed(ActionEvent evnmt) {

		if(evnmt.getSource() == "incrémente"){
			test.inc_numero();
		}
		else if(evnmt.getSource() == "décrémente"){
			test.dec_numero();
		}
		else if (evnmt.getSource() == "affiche"){
			test.affiche_numero();
		}
		else{
			System.out.println("un autre bouton svp");
		}

	}*/









	public class Panneau extends JPanel implements Runnable{
		static final long serialVersionUID = 1;	
		private boolean b_run;
		private int hauteur_dess_route;


		public Panneau(){
			this.hauteur_dess_route = 140;
		}


		//methode de dessin
		public void paintComponent(Graphics g){

			super.paintComponents(g);

			int nb_case = liste.length; //nombre de cellule à dessiner sur la route
			int taille_case = (this.getWidth()-10)/nb_case; // taille des cases à dessiner
			int marge = 5; //les marges à gauche et à droite de la route

			g.setColor(Color.blue); //fond de la fenêtre d'interface
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			g.setColor(Color.gray); //dessin de la route (x1,y1,x2,y2)
			g.fillRect(marge, this.getHeight()/2-hauteur_dess_route/2, this.getWidth()-2*marge, hauteur_dess_route);

			g.setColor(Color.black); //dessin des cellules
			for(int i=1;i<nb_case;i++){
				g.drawLine(5+taille_case*i, this.getHeight()/2-hauteur_dess_route/2, marge+taille_case*i, this.getHeight()/2+hauteur_dess_route/2);
			}

			g.setColor(Color.red);
			for(int k=0; k<liste.length; k++){
				
				//dessine une voiture dans la case
				if(liste[k]>0){
					g.fillRect(taille_case*(k)+marge+4, this.getHeight()/2-hauteur_dess_route/2+15, taille_case-5, hauteur_dess_route-2*15);
				}
			}

		}

		

		//methode abstract venant de implements Runnable
		public void run(){
			//System.out.println("on est bien là!");
			while(b_run){
				route.step();

				repaint();
				route.ecrireDensiteText();
				route.ecrireVoitureText();
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void set_bool_anim(boolean b){
			this.b_run = b;
		}

	}
}





