package projet_log;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Combobox extends JComboBox implements ActionListener{
	private String nom;
	private Fond fen_reglage;
	private Fond fen_simulation;
	private JPanel panel_box;
	private int vit=0;

	public Combobox(Fond f_reglage, Fond f_simulation, String nom ){
		this.fen_reglage = f_reglage;
		this.fen_simulation=f_simulation;
		this.nom = nom;
		this.setPreferredSize(new Dimension(100,20));

		this.addActionListener(this);
		this.addItem("vitesse lente");
		this.addItem("vitesse moyenne");
		this.addItem("vitesse rapide");	
	}

	
	public void actionPerformed(ActionEvent e){
		String choix = (String)this.getSelectedItem();
		
		if (choix == "vitesse lente"){
			this.vit = 1;
		}
		else if(choix == "vitesse moyenne"){
			this.vit = 2;
		}
		else{
			this.vit = 3;	
		}
		fen_simulation.set_vitesse(vit);
		System.out.println("vit = "+vit);

	}

}
