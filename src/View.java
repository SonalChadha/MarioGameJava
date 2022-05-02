import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Iterator;
import java.awt.Color;

class View extends JPanel
{
	Model model;
	static Image background_image;
	
	View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		if(background_image == null)
			background_image = loadImage("background.png");
	}

	static Image loadImage(String filename) {
		Image img = null;
		try {
			img = ImageIO.read(new File(filename));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return img;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// draws background image that scrolls as Mario runs
		for(int i = 0; i < 5; i++) {			
			g.drawImage(background_image, (i*890) - (model.mario.x - model.mario.marioScreenLocation) / 10, 0, this.getWidth(), this.getHeight(), null);
		}
		
		Iterator<Sprite> itr = model.sprites.iterator();
		Sprite s;
		while(itr.hasNext()) // iterate through sprites
		{
			s = itr.next();
			s.draw(g);	// uses polymorphism to call the draw() of either Mario, Brick, CoinBlock, or Coin
		}
	}
}
