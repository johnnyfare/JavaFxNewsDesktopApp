package application;

public class Post {
private String Title;
private String Text;
private String date;
private String time;
private boolean nbOfTimeClicked=false;
 int postID;
 int numberOfLikes;
 public int userID;
 public String AuthorName;
 public Post(String title, String text, String date, String time,int userID) {
	Title = title;
	Text = text;
	this.date = date;
	this.time = time;
	this.userID= userID;
 }

public Post() {
	super();
}

public int getNumberOfLikes() {
	return numberOfLikes;
}

public void setNumberOfLikes(int numberOfLikes) {
	this.numberOfLikes = numberOfLikes;
}

public String getTitle() {
	return Title;
}
public void setTitle(String title) {
	Title = title;
}
public String getText() {
	return Text;
}
public void setText(String text) {
	Text = text;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}

public void setUser(int userId) {
	this.userID=userId;
}

public String getUsername() {
	return this.AuthorName;
}
public void setUsername(String username) {
	this.AuthorName=username;
}
@Override
public String toString() {
	return "Post [Title=" + Title + ", Text=" + Text + ", postID=" + postID + ", date=" + date + ", time=" + time + "]";
}

public boolean getNbOfTimeClicked() {
	return this.nbOfTimeClicked;
}

public void setNbOfTimeClicked() {
	this.nbOfTimeClicked=true;
}
 
 
}
