package application;

import java.io.IOException;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Add {
	@FXML
	private Button button;
	@FXML 
	private TextField textfield;
	@FXML
	private TextArea area;
	
	public void sendPostAdd(ActionEvent action) {
		PstDao pst= new PstDao();
		int AMORPM;
		String AMPM;
		Calendar calendar = Calendar.getInstance();
		int j=calendar.get(Calendar.MONTH)+1;
		//System.out.println(calendar.get(Calendar.YEAR));
		String Time=calendar.get(Calendar.YEAR)+"-"+j+"-"+calendar.get(Calendar.DAY_OF_MONTH);
		String timeoftheday;
		int hour= calendar.get(Calendar.HOUR);
		int minutes= calendar.get(Calendar.MINUTE);
		AMORPM = calendar.get(Calendar.AM_PM);
		if(AMORPM==1) {
			AMPM="PM";
		}
		else {
			AMPM="AM";
		}
		timeoftheday=hour+":"+minutes+" "+AMPM;
		//System.out.println(calendar.get(Calendar.HOUR));
		Post p = new Post(textfield.getText().toString(),area.getText().toString(),Time,timeoftheday, User.userId);
		if(pst.insertPost(p)==1) {
			try {
			Stage stage= (Stage) textfield.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("SampleHome2.fxml"));
			Scene scene = new Scene(root,1000,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			//invalid
		}
	}
	
	public void Home(ActionEvent event) {
		try {
			Stage stage= (Stage) textfield.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("SampleHome2.fxml"));
			Scene scene = new Scene(root,1000,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			stage.setX(250);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Post(ActionEvent event) {

		try {
			Stage stage= (Stage) textfield.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("MyPost.fxml"));
			Scene scene = new Scene(root,1000,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			stage.setX(250);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
