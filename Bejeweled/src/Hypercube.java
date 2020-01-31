import java.awt.Color;
import java.awt.Image;

public class Hypercube extends Jewel {
	static Image hypercubeImage;
	// I found the locations below by a little bit of guess and 
	// check to find a rectangle that bounded the image of the 
	// Emerald.
	static final int HC_X=1450, HC_Y=180, HC_W = 120, HC_H=100;
	
	public Hypercube(int r, int co) {
		super(Color.RED, getImage(), r, co);
	}

	private static Image getImage() {
		if(hypercubeImage == null)
			hypercubeImage = openImageFromSpriteSheet(HC_X, HC_Y, HC_W, HC_H);
		return hypercubeImage;
	}

}
