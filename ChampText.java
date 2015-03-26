package projet_log;

import java.awt.Dimension;

import javax.swing.JTextField;

public class ChampText extends JTextField {

	static final long serialVersionUID = 1;
	private Fond fen_reglage;
	


	public ChampText(Fond f_reglage, String text_init ){
	
		this.fen_reglage = f_reglage;
		this.setPreferredSize(new Dimension(100,20));
		this.setText(text_init);
	}

}

