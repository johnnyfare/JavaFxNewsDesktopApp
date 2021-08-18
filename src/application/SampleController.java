package application;

import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.prism.paint.Color;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SampleController  {
	
	@FXML
	private TextField userName;
	
	@FXML
	private TextField password;
	@FXML
	private Text invalid;

	Connection con=null;
	
	public void Login(ActionEvent event) throws IOException{
			try {
				con=DataBase.getConnection();
				Statement statement=con.createStatement();
				String sql = "select * from users where username='"+this.userName.getText().toString()+"' and password='"+this.password.getText().toString()+"'";
				ResultSet rs = statement.executeQuery(sql);
				if(rs.next()) {
					String usernameTwo = rs.getString("username");
					String password = rs.getString("password");
					String journalist= rs.getString("IsJournalist");
					int id=rs.getInt("userId");
					User user =User.getInstance();
					user.setUsername(usernameTwo);
					user.setPassword(password);
					user.userId=id;
					user.setIsJournalist(journalist);
					//System.out.println(user.toString());
					Stage stage= (Stage) userName.getScene().getWindow();
					Parent root = FXMLLoader.load(getClass().getResource("SampleHome2.fxml"));
					Scene scene = new Scene(root,1000,600);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					stage.setScene(scene);
					stage.show();
					stage.setX(250);
					
				}
				else {
					invalid.setText("Invalid password");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void signin(ActionEvent event) throws IOException {
		Stage stage= (Stage) password.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		stage.setX(400);
	}

}