package projet_log;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Affichage extends JFrame {
	static final long serialVersionUID = 1;
	private Trace trace;
	private double[][] matrice;
	private int tMax;
	private int longMax;



	public Affichage(){
		
		this.trace = new Trace();
		trace.setPreferredSize(new Dimension(600, 600)); //
		trace.setLayout(null);//
		this.setTitle("Affichage densite");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(this.getWidth(),5);
		
		this.setContentPane(trace);
		this.setForeground(Color.gray);
		this.setVisible(true);
	}
	
	public void setMatrice(double[][]matrice){
		this.matrice=matrice;
		longMax=matrice.length;
		tMax=matrice[0].length;
		trace.init_dim();
	}
	public void refresh(){
		trace.repaint();
	}





	//=========================================================================//
	public class Trace extends JPanel{
		static final long serialVersionUID = 1;
		private int longueur_case;
		private int hauteur_case;



		public void init_dim(){
			longueur_case=Math.round(this.getWidth()/longMax);
			hauteur_case=Math.round(this.getHeight()/tMax);
			
		}

		
		public void paintComponent(Graphics g){
			int R;
			int G;
			int B;
			super.paintComponents(g);
			for(int i=0;i<tMax;i++){//parcour le temps
				for(int k=0;k<longMax;k++){//parcour l'espace
					R=(int)(matrice[k][i]*255);
					if(matrice[k][i]<0.5){
						G=(int)(matrice[k][i]*512);
					}else{
						G=(int)(512-matrice[k][i]*512);
					}
					B=(int)(255-matrice[k][i]*255);
					Color densite=new Color(R, G, B);
					g.setColor(densite);
					g.fillRect(k*longueur_case,this.getHeight()-i*hauteur_case,longueur_case,hauteur_case);//xdebut,ydebut,largeur,hauteur

				}
			}
		}
	}
}