package projet_log;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

public class ChampText extends JTextField implements MouseListener {

	static final long serialVersionUID = 1;
	private Fond fen_reglage;
	private String text_init;


	public ChampText(Fond f_reglage, String text_init ){

		this.fen_reglage = f_reglage;
		this.text_init = text_init;
		this.setText(text_init);
		this.addMouseListener(this);

	}
	
	//efface le contenue de la case lorsque l'on clique dedans
	public void mouseClicked(MouseEvent arg0) {
		this.setSelectionStart(0);
		this.setSelectionEnd(this.getText().length());
		this.setText("");

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void raz(){
		this.setText(text_init);
	}

}

