import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keyS;
	boolean keyL;
	boolean keySpace;
	boolean edit = false;
	int mousePressX;
	int mousePressY;
	
	Controller(Model m) {
		model = m;
		Json ob = Json.load("map.json");
		model.unmarshal(ob);
	}
	
	public void actionPerformed(ActionEvent e)
	{
	}

	void setView(View v) {
		view = v;
	}
	
	public void mousePressed(MouseEvent e) {
		mousePressX = e.getX();
		mousePressY = e.getY();
	}
	
	public void mouseReleased(MouseEvent e) {
		int x2 = e.getX();
		int y2 = e.getY();
		if(edit)
			model.createBrick(Math.min(mousePressX, x2) + model.mario.x - model.mario.marioScreenLocation, Math.min(mousePressY, y2), Math.abs(mousePressX - x2), Math.abs(mousePressY - y2));
	}
	
	public void mouseEntered(MouseEvent e) {	
	}
	
	public void mouseExited(MouseEvent e) {
	}
	
	public void mouseClicked(MouseEvent e) {
//		if(e.getY() < 100)
//		{
//			System.out.println("break here");
//		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_SPACE: keySpace = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; 
			{
				model.mario.moveMarioRight = false;	
				break;
			}
			case KeyEvent.VK_LEFT: keyLeft = false; 
			{
				
				model.mario.moveMarioLeft = false;
				break;
			}
			case KeyEvent.VK_SPACE: keySpace = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_ESCAPE: 
			{
				//System.out.println("Exiting now...");
				System.exit(0);
				break;
			}
		}
			char keyChar = e.getKeyChar();
			if(keyChar == 's' || keyChar == 'S')
			{
				model.marshal().save("map.json");
				//System.out.println("map saved...");
			}
			if(keyChar == 'l' || keyChar == 'L')
			{
				Json ob = Json.load("map.json");
				model.unmarshal(ob);
				//System.out.println("map loaded...");
			}
			if(keyChar == 'q' || keyChar == 'Q')
			{
				//System.out.println("Exiting now...");
				System.exit(0);
			}
			if(keyChar == 'e' || keyChar == 'E')
			{
				edit = !edit;
			}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		model.mario.prev_destination();
		if(keyRight) {
			model.mario.x += 7;
			model.mario.moveMarioRight = true;
		}
		if(keyLeft) {
			model.mario.x -= 7;
			model.mario.moveMarioLeft = true;
		}
		if(keySpace) {
			if (model.mario.jumpFrame < 5)                    
            {
                model.mario.vert_velocity -= 5.4;
                model.mario.y += model.mario.vert_velocity;
            }
		}
	}
}
