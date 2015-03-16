package projet_log;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fond extends JFrame  implements ActionListener{
	static final long serialVersionUID = 1;	

	private route route;
	private Boutons1 b_startstop;
	private Boutons1 b_increment;
	private Panneau fond;
	private boolean compteur = false;
	private Thread t;
	private int[] liste;
	private JLabel compteur_step = new JLabel(); 
	private JLabel label_num_voit[];
	private boolean modeDensite;
	private Combobox boite_combo;
	private JPanel panel_reglage;
	private int vit_index;

	public Fond(route route){

		this.setVisible(true);
		this.fond = new Panneau();
		fond.setPreferredSize(new Dimension(640, 480)); //
		fond.setLayout(null);//
		this.setTitle("Interface Graphique");
		this.setSize(4000, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.route=route;
		this.liste = route.get_route();


		//les boutons
		b_increment = new Boutons1("incrémente", this);
		b_startstop = new Boutons1("start/stop", this);
		b_startstop.setBounds(this.getWidth()/2-60, 10,90,50);//
		b_increment.setBounds(this.getWidth()/2+60, 10,100,50);//
		fond.add(b_startstop);
		fond.add(b_increment);

		//les labels

		//label compteur de steps
		Font police = new Font("Tahoma", Font.BOLD, 18); 
		compteur_step.setFont(police);
		compteur_step.setForeground(Color.BLACK);
		compteur_step.setBounds(10, 5,500,100);//
		//compteur_step.setBorder(BorderFactory.createLineBorder(Color.blue, 4));
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
		//this.getContentPane().setLayout(null);
		this.getContentPane().add(fond);

	}
	
	public Fond(String nom_box,Fond f_simulation){
		this.boite_combo = new Combobox(this,f_simulation, nom_box);
		this.setTitle(nom_box);
		this.setSize(300, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(10 ,40);

		this.panel_reglage = new JPanel();
		panel_reglage.setBackground(Color.white);
		panel_reglage.setLayout(new BorderLayout());
		panel_reglage.add(boite_combo, BorderLayout.NORTH);
		this.setContentPane(panel_reglage);

		this.setVisible(true);
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
	public void set_vitesse(int vit_index){
		this.vit_index=vit_index;
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

			int marge = 5; //les marges à gauche et à droite de la route
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
			int count = 0;
			for(int k=0; k<liste.length; k++){

				if(liste[k]>0){

					g.fillRect(taille_case*k+marge+4, this.getHeight()/2-hauteur_dess_route/2+15, taille_case-5, hauteur_dess_route-2*15);


					compteur_step.setText("incrément n° " + route.get_temps());
					label_num_voit[liste[k]-1].setBounds(taille_case*k+marge+taille_case/2-4, this.getHeight()/2-hauteur_dess_route/2+15, taille_case-5, hauteur_dess_route-2*15);
					count++;
				}
			}

		}



		//methode abstract venant de implements Runnable
		public void run(){
			//System.out.println("on est bien là!");
			set_vitesse_animation(vit_index);
			while(b_run){
				
				route.step();

				repaint();
				if(modeDensite){
					//route.ecrireDensiteText();
					//route.ecrireVoitureText();
				}

				try {
					//Thread.sleep(300);
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
			System.out.println("la vitesse est à " + vit_anim);
		}

		public void set_bool_anim(boolean b){
			this.b_run = b;
		}

	}
}





