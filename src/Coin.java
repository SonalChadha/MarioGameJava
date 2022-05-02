import java.util.Random;
import java.awt.Image;
import java.awt.Graphics;

class Coin extends Sprite
{
    Model model;
    static Image coin = null;
    int horiz_velocity;                  

    Coin(int X, int Y, Model m)
    {           
        model = m;                         
        x = X;
        y = Y;
        w = 60;                        
        h = 60;                        
        vert_velocity = -20.0;   // accelerates coin by constant amount first upwards, then down
        Random rand = new Random();       // random number initialization  
        horiz_velocity = rand.nextInt(25) - 12;  //accelerates coin by random horizontal amount
  
        if (coin == null)
        	coin = View.loadImage("coin.png");
    }
	
    @Override
    boolean isMario()
    {
        return false;
    }
    
    @Override
    boolean isBrick()
    {
        return false;
    }
    
    @Override
    boolean isCoinBlock()
    {
        return false;
    }

    @Override
    void draw(Graphics g)
    {
        g.drawImage(coin, x - model.mario.x + model.mario.marioScreenLocation, y, w , h, null);
    }

    @Override
    void update()
    {
        if (y < 500)     // if coin is above ground      
        {
        	x += horiz_velocity; 
        
        	vert_velocity += 1; // to make coin rise
            y += vert_velocity; 
            vert_velocity += 1.6;	// to make coin fall
            y += vert_velocity;
        }
        // remove coin if it hits ground
        else {
        	model.removeCoin(this);
        	y = 500;
        }
    }
}
