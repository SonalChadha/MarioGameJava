import java.awt.Image;
import java.awt.Graphics;

public class Mario extends Sprite {
	int prev_x, prev_y; // stores previous x and y coordinates of Mario
	int coinPop; // for only one coin to pop out of the coin block at a time
	static Image mario_imagesRight[] = null;        
    static Image mario_imagesLeft[] = null;
    static Image mario_img = null;
    int leftImgCounter = 0; // to cycle through the left running images of Mario
    int rightImgCounter = 0; // to cycle through the right running images of Mario
    boolean moveMarioLeft = false; // is set to true if left key is pressed to allow Mario to switch images as if he is running left
	boolean moveMarioRight = false; // is set to true if right key is pressed to allow Mario to switch images as if he is running right
	int marioScreenLocation;
	int defaultPos = -1; // used when Mario is not moving but to determine if he is facing right or left to accordingly switch his image to left image or right image
	int jumpFrame; 
	Model model;
	
    public Mario(Model m)
    {
    	marioScreenLocation = 150;
    	x = 0;
    	y = 0;
    	w = 60;
    	h = 95;
    	jumpFrame = 0;
    	vert_velocity = 0;
    	if (mario_imagesRight == null && mario_imagesLeft == null) {
	        mario_imagesRight = new Image[5];
	        mario_imagesLeft = new Image[5];
	        
            mario_imagesRight[0] = View.loadImage("marioRight1.png");
            mario_imagesRight[1] = View.loadImage("marioRight2.png");
            mario_imagesRight[2] = View.loadImage("marioRight3.png");
            mario_imagesRight[3] = View.loadImage("marioRight4.png");
            mario_imagesRight[4] = View.loadImage("marioRight5.png");
            mario_imagesLeft[0] = View.loadImage("marioLeft1.png");
            mario_imagesLeft[1] = View.loadImage("marioLeft2.png");
            mario_imagesLeft[2] = View.loadImage("marioLeft3.png");
            mario_imagesLeft[3] = View.loadImage("marioLeft4.png");
            mario_imagesLeft[4] = View.loadImage("marioLeft5.png");
	        } 
    }
    
    void prev_destination() {
    	prev_x = x;
    	prev_y = y;
    }
    
    @Override
    void draw(Graphics g)
    {
        if (moveMarioRight)
        {
        	mario_img = mario_imagesRight[rightImgCounter];
            rightImgCounter++;
            
            if (rightImgCounter == 5)
            	rightImgCounter = 0;
            defaultPos = 1;
        }
        else if (moveMarioLeft)
        {
        	mario_img = mario_imagesLeft[leftImgCounter]; 
            leftImgCounter++;
            
            if (leftImgCounter == 5)
            	leftImgCounter = 0;
            defaultPos = 0;
        }
        else if(defaultPos == 1) {
        	mario_img = mario_imagesRight[rightImgCounter];
        }
        else if(defaultPos == 0) {
        	mario_img = mario_imagesLeft[leftImgCounter];
        }
        else {
        	mario_img = mario_imagesRight[rightImgCounter];
        }
        g.drawImage(mario_img, marioScreenLocation, y, w, h, null);
    }
        
    @Override
	public void update()
    {
    	if(y < 520) {
	    	vert_velocity += 1.2; // gravity - release Mario gradually from air to ground
	    	y += vert_velocity;
	    	jumpFrame++;
    	}
    	else						// stop Mario at ground 
    	{
    		vert_velocity = 0.0;
    		y = 520;
    		jumpFrame = 0;
    		coinPop = 0;
    	}
    }
	
	@Override 
	boolean isMario() {
		return true;
	}
	
	@Override 
	boolean isBrick() {
		return false;
	}
	
	@Override 
	boolean isCoinBlock() {
		return false;
	}
}