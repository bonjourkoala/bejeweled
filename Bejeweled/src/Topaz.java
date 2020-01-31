import java.awt.Color;
import java.awt.Image;

public class Topaz extends Jewel {
	static Image topazImage;
	static final int TO_X=685, TO_Y=300, TO_W = 120, TO_H=100;

	public Topaz(int r, int co) {
		super(Color.BLUE, getImage(), r, co);
	}

	private static Image getImage() {
		if(topazImage == null)
			topazImage= openImageFromSpriteSheet(TO_X, TO_Y, TO_W, TO_H);
		return topazImage;
	}

}
