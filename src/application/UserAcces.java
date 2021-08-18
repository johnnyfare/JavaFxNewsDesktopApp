package application;
import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserAcces {
	
	
private Connection con;

	public UserAcces()  {
			   try {
				con= DataBase.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public int insertUser(User user) {
		int insert=0;
		try {
			PreparedStatement ps = con.prepareStatement("insert into users(username,email,password,userID,ISJOURNALIST) values(?,?,?,id_seq.nextval,?)");
			ps.setString(1,user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3,user.getPassword());
			ps.setString(4,user.returnIsJournalist());
			insert= ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return insert;
	}
	
	
	private User getUser(int id) {
		User user = User.getInstance();
		try {
			PreparedStatement ps = con.prepareStatement("select * from users where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user.setUsername(rs.getString(1));
				user.setEmail(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setUserId(rs.getInt(4));
			}
			con.close();
		}catch(Exception ex) {
			ex.printStackTrace();
			
		}
		return user;
	}
	
	public boolean checkUser(String journalist) {
		boolean val=false;
		try {
			PreparedStatement ps = con.prepareStatement("select * from journalist where journalistid=?");
			ps.setString(1, journalist);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				val= true;
			}
			else {
				val= false;
			}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		return val;
	}
}
