package projet_log;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Fond extends JFrame  implements ActionListener{
	static final long serialVersionUID = 1;	


	private static int vit_index;

	//variables utilisées dans l'interface graphique
	private route route;
	private Boutons1 b_startstop;
	private Boutons1 b_increment;
	private Panneau fond;
	private boolean compteur = false;
	private int[] liste;
	private JLabel compteur_step = new JLabel(); 
	private JLabel label_num_voit[];
	private Affichage affichage;
	private Thread t;


	//variable utilisées dans la fenetre de réglage
	private Boutons1 b_ok;
	private boolean modeDensite;
	private Semaphore sema;

	//les champs 
	private JRadioButton seuil_on;
	private JRadioButton seuil_off;
	private ButtonGroup bg = new ButtonGroup();
	private Combobox boite_combo;
	private ChampText champ_voit;
	private ChampText champ_route;
	private ChampText champ_vmax;
	private ChampText champ_proba1;
	private ChampText champ_proba2;
	private ChampText champ_proba3;
	private ChampText champ_seuil;
	private ChampText champ_nb_increment;
	private boolean reg = false; //est-ce qu'on met une régulation des bouchons en place y/n

	//les labels
	private JLabel L_increment;
	private JLabel L_vitesse;
	private JLabel L_voit;
	private JLabel L_route;
	private JLabel L_proba1;
	private JLabel L_proba2;
	private JLabel L_proba3;
	private JLabel L_seuil;
	private JLabel L_vit_anim;

	private JPanel panel_reglage;
	private Parametrage f_parametrage;


	//constructeur de la fentre d'affichage
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
		this.setVisible(true);
	}


	//constructeur de la fenetre de réglage
	public Fond(String nom_box, Semaphore sema){
		f_parametrage = new Parametrage();
		this.sema = sema;

		//les boites de dialogue
		this.boite_combo = new Combobox(this);
		this.champ_nb_increment = new ChampText(this, "200");
		this.champ_voit = new ChampText(this,"20");
		this.champ_route = new ChampText(this,"50");
		this.champ_vmax = new ChampText(this,"5");
		this.champ_proba1 = new ChampText(this, "0.1");
		this.champ_proba2 = new ChampText(this,"0.25");
		this.champ_proba3 = new ChampText(this,"0.08");
		this.champ_seuil = new ChampText(this,"0.8");

		//les radiobuttons
		this.seuil_on = new JRadioButton("activer les seuils");
		this.seuil_off = new JRadioButton("desactiver les seuils");
		this.seuil_off.setSelected(true);
		seuil_on.addActionListener(this);
		seuil_off.addActionListener(this);
		bg.add(seuil_off);
		bg.add(seuil_on);

		//les labels de la f_regalge
		this.L_route = new JLabel("taille de la route");
		this.L_increment = new JLabel("nombre d'incrément voulu");
		this.L_seuil = new JLabel("valeur du seuil plancher");
		this.L_voit = new JLabel("nombre de voiture");
		this.L_vitesse = new JLabel("vitesse maximale des voitures");
		this.L_proba1 = new JLabel("probabilité de freiner sans raison");
		this.L_proba2 = new JLabel("probabilité de ne pas redémarer immédiatement");
		this.L_proba3 = new JLabel("probabilité de freiner brusquement");
		this.L_vit_anim = new JLabel("vitesse de l'animation");


		this.setTitle(nom_box);
		//this.setSize(250, 200);
		this.setLocation(5 ,5);

		b_ok = new Boutons1("ok", this);
		this.panel_reglage = new JPanel();
		panel_reglage.setBackground(Color.white);
		panel_reglage.setLayout(new GridLayout(12,2));

		//on ajoute les label et les champs dans l'ordre.
		panel_reglage.add(L_vit_anim);
		panel_reglage.add(boite_combo);
		panel_reglage.add(seuil_on);
		panel_reglage.add(seuil_off);
		panel_reglage.add(L_increment);
		panel_reglage.add(champ_nb_increment);
		panel_reglage.add(L_voit);
		panel_reglage.add(champ_voit);
		panel_reglage.add(L_route);
		panel_reglage.add(champ_route);
		panel_reglage.add(L_vitesse);
		panel_reglage.add(champ_vmax);
		panel_reglage.add(L_proba1);
		panel_reglage.add(champ_proba1);
		panel_reglage.add(L_proba2);
		panel_reglage.add(champ_proba2);
		panel_reglage.add(L_proba3);
		panel_reglage.add(champ_proba3);
		panel_reglage.add(L_seuil);
		panel_reglage.add(champ_seuil);


		//ceci à la fin
		panel_reglage.add(b_ok);
		this.setContentPane(panel_reglage);
		this.pack();
		this.setResizable(false);
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

			//gestion de la régulation oui/non
			if (seuil_on.isSelected()){
				reg = true;
			}
			else{
				reg = false;
			}

			//vérification du format contenue dans les cases 
			try{
				Integer.valueOf(champ_voit.getText());
				Integer.valueOf(champ_route.getText());
				Integer.valueOf(champ_vmax.getText());
				Integer.valueOf(champ_nb_increment.getText());
				//E.valueOf(arg0)
				Double.valueOf(champ_proba1.getText());
				Double.valueOf(champ_proba2.getText());
				Double.valueOf(champ_proba3.getText());
				Double.valueOf(champ_seuil.getText());

				//enregistrement des parametres rentrés par l'utilisateur dans le tableau de la clase parametrage
				f_parametrage.set_parametres(Integer.valueOf(champ_voit.getText()), Integer.valueOf(champ_route.getText()), Integer.valueOf(champ_vmax.getText()), Integer.valueOf(champ_nb_increment.getText()), Double.valueOf(champ_proba1.getText()),  Double.valueOf(champ_proba2.getText()),  Double.valueOf(champ_proba3.getText()),  Double.valueOf(champ_seuil.getText()), reg);
				sema.release();

			} catch (NumberFormatException e) {
				System.out.println("les paramètres entrés n'ont pas le bon format");
			}


		}

		else{

		}
	}


	public Parametrage get_Parametrage(){
		return f_parametrage;
	}

	public void set_vitesse(int vit_index){
		this.vit_index=vit_index;
	}



	//======================================================================================================================================//
	//=====================================================================================================================================//
	//====================================================================================================================================//


	//classe interne qui étent JPanel (le "background" de la JFrame
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
			int nb_case = liste.length; //nombre de cellule à dessiner sur la route
			int largueur=this.getWidth();
			int hauteur= this.getHeight();

			super.paintComponents(g);

			int marge = 5; //les marges à gauche et ï¿½ droite de la route
			int taille_case = Math.round((this.getWidth()-2*marge)/nb_case); // taille des cases à dessiner

			g.setColor(Color.blue); //fond de la fenêtre d'interface
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
			set_vitesse_animation(vit_index);
			while(b_run){

				if(!route.get_stop()){
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
