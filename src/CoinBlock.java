import java.awt.Image;
import java.awt.Graphics;

class CoinBlock extends Sprite
{
	Model model;
	int coinLimit;                         // limits the number of coins to pop out from coin block     
	static Image coinBrick = null, coinBrickEmpty = null;         

    CoinBlock(int X, int Y, Model m)
    {
        model = m;
        x = X;
        y = Y;
        w = 89;
        h = 83;
        
        if (coinBrick == null && coinBrickEmpty == null)
        {	
            coinBrick = View.loadImage("coinBlock.png"); // load filled coin block image
            coinBrickEmpty = View.loadImage("coinBlockEmpty.png"); // load empty coin block image
        }
    }

    // to load Coin blocks from Json file to program
    public CoinBlock(Json ob, Model m) {
		x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
        h = (int)ob.getLong("h");
        coinLimit = (int)ob.getLong("coinLimit");
        
        model = m;
        if (coinBrick == null && coinBrickEmpty == null)
        {	
            coinBrick = View.loadImage("coinBlock.png");
            coinBrickEmpty = View.loadImage("coinBlockEmpty.png");
        }
	}
	
    // to save Coin blocks into Json file
	Json marshal() {
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("coinLimit", coinLimit);
		return ob;
	}
	
    @Override
    void draw(Graphics g)
    {
        if (this.coinLimit < 5)
            g.drawImage(coinBrick, x - model.mario.x + model.mario.marioScreenLocation, y, w , h, null);
        // change coin block image to being empty after 5 coins have popped out of it
        else
            g.drawImage(coinBrickEmpty, x - model.mario.x + model.mario.marioScreenLocation, y, w , h ,null);
    }

    void addCoins(Model m)
    {
    	// more coins can be added if less than 5 coins have popped out of block
        if(coinLimit < 5)
        {
            coinLimit++;                         
            // create new coin object and add it to sprites list
            Coin coin = new Coin(x, y - 60, m);     
            m.sprites.add(coin);                    
        }
    }
    
    @Override
    void update()
    {
    }
    
    @Override 
	boolean isMario() {
		return false;
	}
	
	@Override 
	boolean isBrick() {
		return false;
	}
	
	@Override 
	boolean isCoinBlock() {
		return true;
	}
}