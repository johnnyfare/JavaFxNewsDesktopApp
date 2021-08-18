package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SampleController3 {
	@FXML
	private TextField isjournalisT;
	@FXML 
	private Label TextInvalid;
	@FXML
	private Label passwordInvalid;

	@FXML
	private Label emailInvalid;
	@FXML 
	private Text invlid;
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private TextField email;
	@FXML
	private Label journalistIDIn;
	private UserAcces userAccess = new UserAcces();
	
	public void signIn(ActionEvent event) throws IOException {
		boolean isJournalist=false;
		boolean tru=true;
		if(username.getText().toString().equals("")) {
			TextInvalid.setText("Username is empty");
			tru=false;
		}
		else {
			TextInvalid.setText("");
		}
		if(password.getText().toString().equals("")) {
			passwordInvalid.setText("Password should contain characters");
			tru=false;
		}
		else {
			if(password.getText().toString().length()<10) {
				passwordInvalid.setText("Password too short");
				tru=false;
			}
			else {
			passwordInvalid.setText("");
			}
		}
		if(email.getText().toString().equals("")){
			emailInvalid.setText("Email Empty");
			tru=false;
		}
		else {
			emailInvalid.setText("");
		}
		if(!isjournalisT.getText().toString().equals("")) {
			boolean ok=this.userAccess.checkUser(isjournalisT.getText().toString());
			if(ok) {
				isJournalist=true;
				
			}
			else {
				tru=false;
				journalistIDIn.setText("Invalid id but you can skip it and sign in as a ReadeR");
				isjournalisT.setText("");
			}
		}
		else {
			isJournalist=false;
		}
		if(tru==true) {
			User user = User.getInstance();
			user.setEmail(email.getText().toString());
			user.setPassword(password.getText().toString());
			user.setUsername(username.getText().toString());
			if(isJournalist) {
				user.setIsJournalist("T");
			}
			//System.out.println(user.toString());
			int insert=this.userAccess.insertUser(user);
		
			if(insert==1) {
				Stage stage= (Stage) username.getScene().getWindow();
				Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
				Scene scene = new Scene(root,700,600);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
				stage.setX(400);
			}
			else {
				TextInvalid.setText("username should be unique");
			}
		}
			
	}
	
	public void returnToAction() {
		try {
			
		Stage stage= (Stage) username.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Scene scene = new Scene(root,700,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		stage.setX(400);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
