import java.awt.Color;
import java.awt.Image;

public class Garnet extends Jewel {
	static Image garnetImage;
	static final int GA_X=1200, GA_Y=880, GA_W = 120, GA_H=100;
	
	public Garnet(int r, int co) {
		super(Color.YELLOW, getImage(), r, co);
	}

	private static Image getImage() {
		if(garnetImage == null)
			garnetImage= openImageFromSpriteSheet(GA_X, GA_Y, GA_W, GA_H);
		return garnetImage;
	}

}
