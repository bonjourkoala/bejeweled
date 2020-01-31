import java.awt.Color;
import java.awt.Image;

public class Amethyst extends Jewel {
	private static Color purple = new Color(128,0,128);
	static Image amethystImage;
	static final int AM_X=940, AM_Y=305, AM_W = 120, AM_H=100;
	
	public Amethyst(int r, int co) {
		super(purple, getImage(), r, co); //color is PURPLE
	}

	private static Image getImage() {
		if(amethystImage == null)
			amethystImage= openImageFromSpriteSheet(AM_X, AM_Y, AM_W, AM_H);
		return amethystImage;
	}

}
