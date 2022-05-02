import java.util.ArrayList;

class Model {
	ArrayList<Sprite> sprites;
	Mario mario;
	
	Model() {
		sprites = new ArrayList<Sprite>();
		// have program loaded with bricks and coin bricks automatically
		Json j = Json.load("map.json");
		this.unmarshal(j);
	}
	
	public void createBrick(int x, int y, int w, int h) {
		Brick b = new Brick(x, y, w, h, this);
		sprites.add(b);
	}
	
//	void addCoinBlock()
//    {
//        for (int i = 1; i < 4; i++) // create 3 coin blocks
//        {
//        	// pass in different x and y locations for each coin block 
//            CoinBlock coinB = new CoinBlock(i*610, 400, this);    
//            sprites.add(coinB);                                    
//        }
//    }
	
	void removeCoin(Coin coin)
    {
        sprites.remove(coin);      
    }
	
	// to save all objects in Json file
	Json marshal() {
		Json ob = Json.newObject();
		Json tmpList = Json.newList();
        ob.add("bricks", tmpList); // have list for bricks
        
        Json coinList = Json.newList();
        ob.add("coinBlocks", coinList); // have list for coin blocks, separate from bricks list
        
        for(int i = 0; i < sprites.size(); i++) {
        	Sprite s = sprites.get(i);
        	if(s.isBrick())
        		tmpList.add(((Brick)s).marshal()); // add Brick sprite to its bricks list in Json file
        	else if(s.isCoinBlock())
        		coinList.add(((CoinBlock)s).marshal()); // add CoinBlock sprite to its coinBlocks list in Json file
        }
       	return ob;
	}
	
	// to load all objects from Json file to program
	void unmarshal(Json ob) {
        sprites = new ArrayList<Sprite>();
        mario = new Mario(this);
        sprites.add(mario);
        Json tmpList = ob.get("bricks");
        Json coinList = ob.get("coinBlocks");
        for(int i = 0; i < tmpList.size(); i++)
        	sprites.add(new Brick(tmpList.get(i), this));

        for(int i = 0; i < coinList.size(); i++)
        	sprites.add(new CoinBlock(coinList.get(i), this));
    }
	
	public void update() {
		Sprite s;
		for(int i = 0; i < sprites.size(); i++)
		{
			s = sprites.get(i);

			// if sprite is a brick or coin block that Mario is colliding with, push Mario out
			if(s.isBrick() || s.isCoinBlock()) {
				if(mario.isColliding(s))
					mario.getOutOfTheObstacle(this, mario, s);
			}	
			// uses polymorphism to call the update() of either Mario, CoinBlock, Coin, or Brick
			s.update(); 
		}
    }
}
