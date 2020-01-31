import java.awt.Color;
import java.awt.Image;

public class Sapphire extends Jewel {
	static Image sapphireImage;
	static final int SA_X=688, SA_Y=765, SA_W = 120, SA_H=100;
	
	public Sapphire(int r, int co) {
		super(Color.ORANGE, getImage(), r, co);
	}

	private static Image getImage() {
		if(sapphireImage == null)
			sapphireImage= openImageFromSpriteSheet(SA_X, SA_Y, SA_W, SA_H);
		return sapphireImage;
	}

}
