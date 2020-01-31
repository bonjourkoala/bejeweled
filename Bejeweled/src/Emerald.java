import java.awt.Color;
import java.awt.Image;

public class Emerald extends Jewel {
	static Image emeraldImage;
	static final int EM_X=943, EM_Y=880, EM_W = 120, EM_H=100;
	
	public Emerald(int r, int co) {
		super(Color.GREEN, getImage(), r, co);
	}

	private static Image getImage() {
		if(emeraldImage == null)
			emeraldImage= openImageFromSpriteSheet(EM_X, EM_Y, EM_W, EM_H);
		return emeraldImage;
	}

}
