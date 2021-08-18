package application;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class JournalistCutomer implements ICustomer{

	@Override
	public void addImage(HBox hboX) {
		try {
		Image imageView = new Image(new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ProjectT\\src\\Images\\journalist.png"),70,60,false,false);
		
		ImageView image = new ImageView(imageView);
		hboX.getChildren().add(image);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
			}
}
