package gt;

import java.awt.*;

public class CanvasPanel extends Canvas {

	Dimension size;
	
	public CanvasPanel(int width, int height) {
		size = new Dimension(width, height);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
	}

}
