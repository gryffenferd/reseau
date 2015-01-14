package fr.ensisa.hassenforder.game.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageLoader implements Runnable {

	private Application app;
	private JButton jImage;
	private String image;
	
	public ImageLoader(Application app, String image, JButton jImage) {
		this.app = app;
		this.image = image;
		this.jImage = jImage;
	}

	public void run () {
		String localName;
		if (image != null) localName = app.doGetImage (image);
		else localName = "./empty.png";
		try {
			BufferedImage picture = ImageIO.read(new File(localName));
			if (picture == null) picture = ImageIO.read(new File("./empty.png"));
			jImage.setIcon(new ImageIcon(picture));
			jImage.setText(null);
		} catch (IOException e) {
			jImage.setText("no picture");
			jImage.setIcon(null);
		}
	}
}
