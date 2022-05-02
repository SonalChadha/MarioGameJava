import java.awt.Graphics;
import java.awt.Image;

public class Brick extends Sprite {
	static Image img = null;
	Model model;
	
	public Brick(int X, int Y, int W, int H, Model m) {
		x = X;
		y = Y;
		w = W;
		h = H;
		model = m;
		if(img == null)
			img = View.loadImage("brick.png");
	}

	// load Bricks from Json file to program
	public Brick(Json ob, Model m) {
		x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
        h = (int)ob.getLong("h");
        model = m;
        if(img == null)
			img = View.loadImage("brick.png");
	}
	
	// save Bricks to Json file
	Json marshal() {
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		return ob;
	}
	
	@Override
	void draw(Graphics g) {
		g.drawImage(img, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
	}
	
	@Override
	void update() {
	}
	
	@Override 
	boolean isMario() {
		return false;
	}
	
	@Override 
	boolean isBrick() {
		return true;
	}
	
	@Override 
	boolean isCoinBlock() {
		return false;
	}
}