import java.awt.Color;
import java.awt.Image;

public class Ruby extends Jewel {
	static Image rubyImage;
	static final int RU_X=1070, RU_Y=650, RU_W = 120, RU_H=100;
	
	public Ruby(int r, int co) {
		super(Color.RED, getImage(), r, co);
	}

	private static Image getImage() {
		if(rubyImage == null)
			rubyImage= openImageFromSpriteSheet(RU_X, RU_Y, RU_W, RU_H);
		return rubyImage;
	}

}
