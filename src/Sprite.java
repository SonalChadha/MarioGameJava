import java.awt.Graphics;

abstract class Sprite {
	int x, y, w, h;
	double vert_velocity;
	
	abstract void update();
	
	abstract void draw(Graphics g);
	
	abstract boolean isBrick();      
	abstract boolean isMario();      
    abstract boolean isCoinBlock();  
	
    // checks to see if Mario is colliding with obstacle
	public boolean isColliding(Sprite s) {
		if (this.x + this.w < s.x)      // Not a Left collision
        	return false;
        if (this.x > s.x + s.w)    // Not a Right collision
        	return false;
        if (this.y + this.h < s.y)       // Not Top collision
            return false;
        if (this.y > s.y + s.h)      // Not Under the brick collision 
            return false;  
        return true;
	}
	
	// pushes Mario out of obstacle
	void getOutOfTheObstacle(Model model, Mario m, Sprite s)
	{		
		if (this.x <= s.x + s.w && m.prev_x >= s.x + s.w)      //Right Side Collision
		{
			this.x = s.x + s.w;
		}
		else if (this.x + this.w >= s.x && m.prev_x + this.w <= s.x) {  // Left side collision
			this.x = s.x - this.w;
		}
		else if (this.y + this.h >= s.y && m.prev_y + this.h <= s.y + s.h)  //Top Collision
		{
			this.y = s.y - this.h;
			m.jumpFrame = 0;
			m.vert_velocity = 2.1;
			m.coinPop = 0;
		}
		else if (this.y <= s.y + s.h && m.prev_y >= s.h)          //Under the Brick collision
		{
			this.y = s.y + s.h;
			m.vert_velocity = 0.0; 
			if (s.isCoinBlock() && m.coinPop == 0)                                 
            {         
                m.coinPop++;                                                       
                CoinBlock coinBrick = (CoinBlock)s;                                       
                if(coinBrick.coinLimit < 5)                                                
                	coinBrick.addCoins(model);                 
            }
		}
  }
}
