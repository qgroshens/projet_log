package projet_log;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fond extends JFrame  implements ActionListener{
	static final long serialVersionUID = 1;	

	private route route;
	private Boutons1 b_startstop;
	private Boutons1 b_increment;
	private Boutons1 b_ok;
	private Panneau fond;
	private boolean compteur = false;
	private Thread t;
	private int[] liste;
	private JLabel compteur_step = new JLabel(); 
	private JLabel label_num_voit[];
	private boolean modeDensite;
	private Combobox boite_combo;
	private ChampText champ_voit;
	private ChampText champ_route;
	private ChampText champ_vmax;
	private ChampText champ_proba1;
	private ChampText champ_proba2;
	private ChampText champ_proba3;
	
	private JPanel panel_reglage;
	private int vit_index;
	private Affichage affichage;

	public Fond(route route,Affichage affiche){

		this.setVisible(true);
		this.fond = new Panneau();
		fond.setPreferredSize(new Dimension(640, 480)); 
		fond.setLayout(null);//
		this.setTitle("Interface Graphique");
		this.setSize(4000, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(5,4*this.getHeight()/6);
		this.route=route;
		this.liste = route.get_route();
		this.affichage=affiche;

		//les boutons
		b_increment = new Boutons1("incrément", this);
		b_startstop = new Boutons1("start/stop", this);
		b_startstop.setBounds(this.getWidth()/2-60, 10,90,50);
		b_increment.setBounds(this.getWidth()/2+60, 10,100,50);
		fond.add(b_startstop);
		fond.add(b_increment);

		//les labels

		//label compteur de steps
		Font police = new Font("Tahoma", Font.BOLD, 18); 
		compteur_step.setFont(police);
		compteur_step.setForeground(Color.BLACK);
		compteur_step.setBounds(10, 5,500,100);
		fond.add(compteur_step);

		//labels des numéros de voitures
		label_num_voit = new JLabel[route.get_nb_voit()];
		Font police2 = new Font("Tahoma", Font.BOLD, 15); 

		for(int k=0;k<label_num_voit.length;k++){
			label_num_voit[k]  = new JLabel();
			label_num_voit[k].setText(""+k);
			label_num_voit[k].setFont(police2);
			fond.add(label_num_voit[k]);
		}
		this.getContentPane().add(fond);
	}

	
	public Fond(String nom_box,Fond f_simulation){
		//les boites de dialogue
		this.boite_combo = new Combobox(this,f_simulation);
		this.champ_voit = new ChampText(this,f_simulation, "Nombre de voiture");
		this.champ_route = new ChampText(this,f_simulation, "Taille de la route");
		this.champ_vmax = new ChampText(this,f_simulation, "Vitesse max (case/unité de temps)");
		this.champ_proba1 = new ChampText(this,f_simulation, "Probabilité de ralentir pour rien");
		this.champ_proba2 = new ChampText(this,f_simulation, "Probabilité de ne pas redémarrer");
		this.champ_proba3 = new ChampText(this,f_simulation, "Probabilité de freiner brutalement");

		//du code utile mais chiant
		this.setTitle(nom_box);
		this.setSize(450, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(5 ,5);
		
		b_ok = new Boutons1("ok", this);
		this.panel_reglage = new JPanel();
		panel_reglage.setBackground(Color.white);
		panel_reglage.setLayout(new GridLayout(4,2,3,3));
		
		panel_reglage.add(boite_combo);
		panel_reglage.add(champ_voit);
		panel_reglage.add(champ_route);
		panel_reglage.add(champ_vmax);
		panel_reglage.add(champ_proba1);
		panel_reglage.add(champ_proba2);
		panel_reglage.add(champ_proba3);
		
		
		//celui ci à la fin
		panel_reglage.add(b_ok);
		this.setContentPane(panel_reglage);

		this.setVisible(true);
	}



	public void actionPerformed(ActionEvent evenement) {
		String bouton_appuye = evenement.getActionCommand();

		if(bouton_appuye == "incrément"){
			route.step();
			fond.repaint();
		}
		else if (bouton_appuye == "start/stop"){

			if(compteur == false){
				b_increment.setEnabled(false);
				fond.set_bool_anim(true);
				this.t = new Thread(fond);
				fond.set_bool_anim(true);
				t.start();
				compteur = true;
			}
			else
			{
				fond.set_bool_anim(false);
				compteur = false;
				b_increment.setEnabled(true);
			}
		}
		else if(bouton_appuye == "ok"){
			System.out.println("la valeur entrez dans le champ est : " + champ_voit.getText() );
			//insérer ici le code qui "set" toutes les valeurs présentes dans la fenêtre de réglage, avec la méthode getText() 
			//pour récupérer ce qui est entré au clavier.
		}

		else{
			System.out.println("un autre bouton svp");
		}

	}
	
	public void set_vitesse(int vit_index){
		this.vit_index=vit_index;
	}



	
//======================================================================================================================================//
//=====================================================================================================================================//
//====================================================================================================================================//
	

	public class Panneau extends JPanel implements Runnable{
		static final long serialVersionUID = 1;	
		private boolean b_run;
		private int hauteur_dess_route;
		private int vit_anim;
		


		public Panneau(){
			this.hauteur_dess_route = 140;
		}


		//methode de dessin
		public void paintComponent(Graphics g){

			super.paintComponents(g);		
			int nb_case = liste.length; //nombre de cellule ï¿½ dessiner sur la route
			int largueur=this.getWidth();
			int hauteur= this.getHeight();

			super.paintComponents(g);

			int marge = 5; //les marges ï¿½ gauche et ï¿½ droite de la route
			int taille_case = Math.round((this.getWidth()-2*marge)/nb_case); // taille des cases ï¿½ dessiner

			g.setColor(Color.blue); //fond de la fenï¿½tre d'interface
			g.fillRect(0, 0, largueur, this.getHeight());

			g.setColor(Color.gray); //dessin de la route (x1,y1,x2,y2)
			g.fillRect(marge, hauteur/2-hauteur_dess_route/2, largueur-marge, hauteur_dess_route);

			g.setColor(Color.black); //dessin des cellules
			for(int i=0;i<=nb_case;i++){
				g.drawLine(marge+taille_case*i, hauteur/2-hauteur_dess_route/2, taille_case*i+marge, hauteur/2+hauteur_dess_route/2);
			}

			//dessine les voitures dans les cases
			g.setColor(Color.red);
			for(int k=0; k<liste.length; k++){

				if(liste[k]>0){

					g.fillRect(taille_case*k+marge+4, this.getHeight()/2-hauteur_dess_route/2+15, taille_case-5, hauteur_dess_route-2*15);


					compteur_step.setText("incrément n° " + route.get_temps());
					label_num_voit[liste[k]-1].setBounds(taille_case*k+marge+taille_case/2-4, this.getHeight()/2-hauteur_dess_route/2+15, taille_case-5, hauteur_dess_route-2*15);
				}
			}

		}


		//methode abstract venant de implements Runnable
		public void run(){
			//System.out.println("on est bien lï¿½!");
			set_vitesse_animation(vit_index);
			while(b_run){
				
				route.step();
				affichage.setMatrice(route.get_matrice_densite());
				affichage.refresh();
				repaint();
				if(modeDensite){
					//route.ecrireDensiteText();
					//route.ecrireVoitureText();
				}

				try {
					Thread.sleep(vit_anim);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		public void set_vitesse_animation(int vit){
			if(vit==1){
				vit_anim=250;
			}
			else if(vit==2){
				vit_anim=100;
			}
			else if(vit==3){
				vit_anim=50;
			}else{
				vit_anim=500;
			}
		}

		public void set_bool_anim(boolean b){
			this.b_run = b;
		}

	}
}
