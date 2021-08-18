package application;

public class User {
	
public  String Username;
private String email;
private String password;
private int JournalistID;
private String isJournalist="F";
public static int userId;

private static User instance;

/*public User(String username,String password) {
	super();
	this.Username = username;
	this.password = password;
}*/
public void setIsJournalist(String journalist) {
	this.isJournalist=journalist;
}

public String returnIsJournalist() {
	return this.isJournalist;
}

private User() {
	
}

public static User getInstance(){
    if(instance == null){
        instance = new User();
    }
    return instance;
}
public String getUsername() {
	return Username;
}

public void setUsername(String username) {
	Username = username;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}


public int getJournalistID() {
	return JournalistID;
}

public void setJournalistID(int journalistID) {
	JournalistID = journalistID;
}

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

@Override
public String toString() {
	return "User [Username=" + Username + ", email=" + email + ", password=" + password + "]";
}


}
