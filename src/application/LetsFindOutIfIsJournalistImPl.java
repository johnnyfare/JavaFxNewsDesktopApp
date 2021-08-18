package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LetsFindOutIfIsJournalistImPl implements LetsFindIsJournalist{

	@Override
	public void runCommand(BorderPane id) {
		Button button =new Button("Add Post");
		button.setId("buttonMyAdd");
		VBox v =new VBox(20);
		button.setOnAction(e->{
			try {
			Stage stage= (Stage) button.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("Add.fxml"));
			Scene scene = new Scene(root,1000,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			stage.setX(250);
			}catch(IOException a) {
				a.printStackTrace();
			}
		});
		v.getStyleClass().add("root1");
		v.setPadding(new Insets(150,40,0,12));
		Label label = new Label("Home Page");
		label.setId("HomePageBackground");
		Button buttoTwon =new Button("MyPost");
		buttoTwon.setId("buttonMyAdd");
		buttoTwon.setOnAction(e->{
			try {
			Stage stage= (Stage) button.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("MyPost.fxml"));
			Scene scene = new Scene(root,1000,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			stage.setX(250);
			}catch(IOException a) {
				a.printStackTrace();
			}
		});
		v.getChildren().addAll(label,buttoTwon,button);
		id.setLeft(v);
			}

}
