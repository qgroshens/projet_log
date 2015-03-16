package projet_log;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ChampText extends JTextField implements ActionListener {
	static final long serialVersionUID = 1;
	
	private Fond fen_reglage;
	private Fond fen_simulation;
	

	public ChampText(Fond f_reglage, Fond f_simulation, String text_init ){
		this.fen_reglage = f_reglage;
		this.fen_simulation=f_simulation;
		this.setPreferredSize(new Dimension(100,20));
		this.setText(text_init);
	}
	
	public void test(){
		System.out.println(""+this.getText());
	}
	
	public void actionPerformed(ActionEvent e){
		
	}
}
