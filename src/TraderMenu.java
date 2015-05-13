// Trader Menu will be used for bartering with merchants.
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class TraderMenu extends JPanel implements ActionListener{
	private Game game;

	public TraderMenu(Game game){
		this.game = game;
	    setPreferredSize(new Dimension(game.WIDTH, game.HEIGHT));
	    setAlignmentX(CENTER_ALIGNMENT);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
