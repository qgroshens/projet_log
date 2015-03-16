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



	public Affichage(double[][]matrice){
		this.setVisible(true);
		this.trace = new Trace();
		trace.setPreferredSize(new Dimension(640, 480)); //
		trace.setLayout(null);//
		this.setTitle("Affichage densite");
		this.setSize(4000, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.matrice=matrice;
		longMax=matrice.length;
		tMax=matrice[0].length;
	}





	//=========================================================================//
	public class Trace extends JPanel{
		static final long serialVersionUID = 1;
		private int longueur_case;
		private int hauteur_case;



		public Trace(){
			longueur_case=Math.round(this.getWidth()/longMax);
			hauteur_case=Math.round(this.getHeight()/tMax);
		}

		public void paintComponent(Graphics g){

			super.paintComponents(g);
		}
	}
}