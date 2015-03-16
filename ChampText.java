package projet_log;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

public class ChampText extends JFormattedTextField {
	
	static final long serialVersionUID = 1;
	private Fond fen_reglage;
	private Fond fen_simulation;
	

	public ChampText(Fond f_reglage, Fond f_simulation, String text_init ){
		super(NumberFormat.getIntegerInstance());
		this.fen_reglage = f_reglage;
		this.fen_simulation=f_simulation;
		this.setPreferredSize(new Dimension(100,20));
		this.setText(text_init);
	}
	
}
