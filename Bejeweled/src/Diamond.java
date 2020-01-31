import java.awt.Color;
import java.awt.Image;

public class Diamond extends Jewel {
	static Image diamondImage;
	static final int DI_X=1070, DI_Y=880, DI_W = 120, DI_H=100;
	
	public Diamond(int r, int co) {
		super(Color.WHITE, getImage(), r, co);
	}

	private static Image getImage() {
		if(diamondImage == null)
			diamondImage= openImageFromSpriteSheet(DI_X, DI_Y, DI_W, DI_H);
		return diamondImage;
	}

}
