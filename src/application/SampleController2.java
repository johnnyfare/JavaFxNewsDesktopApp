package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SampleController2 implements Initializable{
@FXML 
private Button button;
@FXML
private HBox hbox;
@FXML
private BorderPane snow;
@FXML 
private VBox Left;
@FXML
private TextField searchEditText; 
@FXML
private Button Logout;

private PstDao post= new PstDao();
ArrayList<Post> arr = new ArrayList<Post>();
GridPane stragey;

//IntegerProperty balance = new SimpleIntegerProperty();

public void handle(ActionEvent event) throws IOException {
	Stage stage= (Stage)button.getScene().getWindow();
	Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
	Scene scene = new Scene(root,700,600);
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	stage.setScene(scene);
	stage.show();
	stage.setX(400);
}

public void openPost(ActionEvent event) {

	try {
		Stage stage= (Stage) button.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("Add.fxml"));
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

public void Logout(ActionEvent event) {
	try {
		Stage stage= (Stage) Logout.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Scene scene = new Scene(root,700,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		stage.setX(250);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	//journalist or user
	if(User.getInstance().returnIsJournalist().equals("T")) {
	BaseCustomerClass basCustomer = new JournalistFactory();
	ICustomer customer = basCustomer.CreateCustomer();
	customer.addImage(hbox);
	}
	else {
		BaseCustomerClass basCustomer = new UserFactory();
		ICustomer customer = basCustomer.CreateCustomer();
		customer.addImage(hbox);
	}
	
	//PublicClasProxyy lestq= new PublicClasProxyy(User.getInstance(),snow);
	//lestq.runCommand(snow);
	LetsFindIsJournalist lestq = new PublicClasProxyy(User.getInstance(),snow);
	lestq.runCommand(snow);
	ArrayList<Integer>Green;
	arr=this.post.retreivePost();
	Green = this.post.getLikes(User.userId);
	ArrayList<Text> titile = new ArrayList<Text>();
	for(Post I : arr) {
		for(int R:Green) {
			if(I.postID==R){
				I.setNbOfTimeClicked();
				/*System.out.println(I.postID);
				System.out.println("Yes");*/
			}
		}
		GridPane e = new GridPane();
		e.setVgap(10);
		e.setHgap(10);
		e.setId("BackgroundGridpane");
		Text textTitle = new Text("Title : ");
		Text textAuthor = new Text("Author: ");
		textAuthor.setText(textAuthor.getText()+I.getUsername());
		textTitle.setText(textTitle.getText()+I.getTitle());
		e.add(textTitle, 0, 0);
			Text time=new Text("Time:");
			time.setText(time.getText()+I.getTime());
			e.add(time,0,0);
			e.setMargin(time,new Insets(0,0,0,500));
			Text Date= new Text("Date:");
			Date.setText(Date.getText()+" "+I.getDate());
			e.setMargin(Date, new Insets(0,00,0,400));
			e.add(Date, 0, 0);
			Text like= new Text();
			/*balance.addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable arg0) {
					// TODO Auto-generated method stub
					System.out.println();
					like.setText(" "+balance.intValue());	
					System.out.println(titile);
					System.out.println(arr.indexOf(I));
					titile.get(arr.indexOf(I)).setText(" "+balance.intValue());
				}
				
			});*/
			like.setText(""+I.getNumberOfLikes());
			titile.add(like);
		e.add(textAuthor, 0, 1);
		Text textThree = new Text();
		textThree.setText(I.getText());
		textThree.setWrappingWidth(600);
		
		e.add(textThree, 0, 4);
		try {
			if(I.getNbOfTimeClicked()==false) {
			Image imageView = new Image(new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ProjectT\\src\\Images\\like.png"),20,20,false,false);
			
			ImageView image = new ImageView(imageView);
			image.setOnMouseClicked(es->{
						try {
						if(I.getNbOfTimeClicked()==false) {	
						//System.out.println(I.toString());
						I.setNbOfTimeClicked();
						I.setNumberOfLikes(I.getNumberOfLikes()+1);
						//System.out.println(arr.indexOf(I));
						titile.get(arr.indexOf(I)).setText(" "+I.getNumberOfLikes());
						Image image2= new Image(new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ProjectT\\src\\Images\\res.jfif"),20,20,false,false);
						PstDao p = new PstDao();
						p.updatePost(I.postID);
						p.insertLike(User.userId,I.postID);
						image.setImage(image2);
						}
						}catch(FileNotFoundException efile) {
							efile.printStackTrace();
						}
		      });
			e.add(image, 0, 5);
			}
			else {
				Image imageView = new Image(new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ProjectT\\src\\Images\\res.jfif"),20,20,false,false);
				
				ImageView image = new ImageView(imageView);
				e.add(image, 0, 5);
			}
			
			e.setMargin(like, new Insets(0,5,0,25));
			e.add(like, 0, 5);
			/*e.setOnMouseClicked(el->{
				try {
				  if (el.getClickCount() == 2) {
					  Image image2= new Image(new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ProjectT\\src\\Images\\res.jfif"),20,20,false,false);
						image.setImage(image2);
				  }
				}catch(FileNotFoundException file) {
					
				}
				});*/
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Left.getChildren().add(e);
	}
}

public void searchOnclick() {
	if(searchEditText.getText().toString().equals("")) {
		
	}
	else {
		//System.out.println(searchEditText.getText().toString());
	ArrayList<Post> postId;
	postId=this.post.retrievePostOnIn(searchEditText.getText().toString());
	//System.out.println(postId);
	arr.clear();
	arr.addAll(postId);
	Left.getChildren().clear();


	
	
	ArrayList<Integer> Green;
	Green = this.post.getLikes(User.userId);
	ArrayList<Text> titile = new ArrayList<Text>();
	//System.out.println(Green);
	for(Post I : arr) {
		for(int R:Green) {
			if(I.postID==R){
				I.setNbOfTimeClicked();
				//System.out.println(I.postID);
				//System.out.println("Yes");
			}
		}
		GridPane e = new GridPane();
		e.setVgap(10);
		e.setHgap(10);
		e.setId("BackgroundGridpane");
		Text textTitle = new Text("Title : ");
		Text textAuthor = new Text("Author: ");
		textAuthor.setText(textAuthor.getText()+I.getUsername());
		textTitle.setText(textTitle.getText()+I.getTitle());
		e.add(textTitle, 0, 0);
			Text time=new Text("Time:");
			time.setText(time.getText()+I.getTime());
			e.add(time,0,0);
			e.setMargin(time,new Insets(0,0,0,500));
			Text Date= new Text("Date:");
			Date.setText(Date.getText()+" "+I.getDate());
			e.setMargin(Date, new Insets(0,00,0,400));
			e.add(Date, 0, 0);
			Text like= new Text();
			/*balance.addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable arg0) {
					// TODO Auto-generated method stub
					System.out.println();
					like.setText(" "+balance.intValue());	
					System.out.println(titile);
					System.out.println(arr.indexOf(I));
					titile.get(arr.indexOf(I)).setText(" "+balance.intValue());
				}
				
			});*/
			like.setText(""+I.getNumberOfLikes());
			titile.add(like);
		e.add(textAuthor, 0, 1);
		Text textThree = new Text();
		textThree.setText(I.getText());
		textThree.setWrappingWidth(600);
		
		e.add(textThree, 0, 4);
		try {
			if(I.getNbOfTimeClicked()==false) {
			Image imageView = new Image(new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ProjectT\\src\\Images\\like.png"),20,20,false,false);
			
			ImageView image = new ImageView(imageView);
			image.setOnMouseClicked(es->{
						try {
						if(I.getNbOfTimeClicked()==false) {	
						//System.out.println(I.toString());
						I.setNbOfTimeClicked();
						I.setNumberOfLikes(I.getNumberOfLikes()+1);
						//System.out.println(arr.indexOf(I));
						titile.get(arr.indexOf(I)).setText(" "+I.getNumberOfLikes());
						Image image2= new Image(new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ProjectT\\src\\Images\\res.jfif"),20,20,false,false);
						PstDao p = new PstDao();
						p.updatePost(I.postID);
						p.insertLike(User.userId,I.postID);
						image.setImage(image2);
						}
						}catch(FileNotFoundException efile) {
							efile.printStackTrace();
						}
		      });
			e.add(image, 0, 5);
			}
			else {
				Image imageView = new Image(new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ProjectT\\src\\Images\\res.jfif"),20,20,false,false);
				
				ImageView image = new ImageView(imageView);
				e.add(image, 0, 5);
			}
			
			e.setMargin(like, new Insets(0,5,0,25));
			e.add(like, 0, 5);
			/*e.setOnMouseClicked(el->{
				try {
				  if (el.getClickCount() == 2) {
					  Image image2= new Image(new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ProjectT\\src\\Images\\res.jfif"),20,20,false,false);
						image.setImage(image2);
				  }
				}catch(FileNotFoundException file) {
					
				}
				});*/
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Left.getChildren().add(e);
	}
	//System.out.println(arr);
	}
}
}
