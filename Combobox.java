package projet_log;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Combobox extends JComboBox implements ActionListener{
	static final long serialVersionUID = 1;
	
	private Fond fen_reglage;
	//private JPanel panel_box;
	private int vit=0;

	public Combobox(Fond f_reglage){
		this.fen_reglage = f_reglage;
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

		fen_reglage.set_vitesse(vit);
		
	}

}
