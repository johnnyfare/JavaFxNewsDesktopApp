package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PstDao {
	
	private Connection con;

	public PstDao()  {
			   try {
				con= DataBase.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public int insertPost(Post post) {
		int insert=0;
		try {
			//System.out.println(post.toString());
			PreparedStatement ps = con.prepareStatement("insert into post(postId,postDate,postTitle,postTime,Text,postUserID) values(Customers_seq.nextval,?,?,?,?,?)");
			ps.setString(1,post.getDate());
			ps.setString(2, post.getTitle());
			ps.setString(3, post.getTime());
			ps.setString(4,post.getText());
			ps.setInt(5,User.userId);
			insert= ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return insert;
	}

	public ArrayList<Post> retreivePost() {
		ArrayList<Post> psot = new ArrayList<Post>();
		int insert=0;
		try {
			PreparedStatement ps = con.prepareStatement("select * from post,Users where post.POSTUSERID=Users.USERID");
			ResultSet rone = ps.executeQuery();
			while(rone.next()) {
				Post p = new Post();
				p.setDate(rone.getString("postDate"));
				p.setTitle(rone.getString("posttitle"));
				p.setText(rone.getString("Text"));
				p.setTime(rone.getString("postTime"));
				p.setUser(rone.getInt("postUserId"));
				p.setNumberOfLikes(rone.getInt("Nboflikes"));
				p.postID=rone.getInt("PostID");
				p.setUsername(rone.getString("Username"));
				psot.add(p);
				
			}
			//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return psot;
	}
	
	public void updatePost(int PostID) {
		try {
			PreparedStatement ps = con.prepareStatement("update post set NBOFLIKes=NBOFLIKES+1 where postId="+PostID);
			ResultSet rone = ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ArrayList<Integer> getLikes(int UserID) {
		ArrayList<Integer> number  = new ArrayList<Integer>();
		try {
			PreparedStatement ps = con.prepareStatement("select postID from likes where UserId="+UserID);
			ResultSet rone = ps.executeQuery();
			while(rone.next()) {
				number.add(rone.getInt("PostID"));
			}
			return number;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return number;
	}
	
	public void insertLike(int UserID,int PostID) {
		try {
			PreparedStatement  ps = con.prepareStatement("insert into likes(userID,postID) values("+UserID+","+PostID+")");
			ResultSet rone = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Post> retrievePostOnIn(String keyword){
		ArrayList<Post> arr =  new ArrayList<Post>();
		
		
		try {
			PreparedStatement ps= con.prepareStatement("select * from post,Users where post.POSTUSERID=Users.USERID and text LIKE '%"+keyword+"%'");
			ResultSet rone = ps.executeQuery();
			while(rone.next()) {
				Post p = new Post();
				p.setDate(rone.getString("postDate"));
				p.setTitle(rone.getString("posttitle"));
				p.setText(rone.getString("Text"));
				p.setTime(rone.getString("postTime"));
				p.setUser(rone.getInt("postUserId"));
				p.setNumberOfLikes(rone.getInt("Nboflikes"));
				p.postID=rone.getInt("PostID");
				p.setUsername(rone.getString("Username"));
				arr.add(p);
			
			}
			return arr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public ArrayList<Post> fetchretreivePostOnId(int UserID){
		ArrayList<Post> arr = new ArrayList<Post>();
		
		try {
			PreparedStatement ps= con.prepareStatement("select * from post,Users where users.userid=post.postuserid and post.postuserid="+UserID+"");
			ResultSet rone = ps.executeQuery();
			while(rone.next()) {
				Post p = new Post();
				p.setDate(rone.getString("postDate"));
				p.setTitle(rone.getString("posttitle"));
				p.setText(rone.getString("Text"));
				p.setTime(rone.getString("postTime"));
				p.setUser(rone.getInt("postUserId"));
				p.setNumberOfLikes(rone.getInt("Nboflikes"));
				p.postID=rone.getInt("PostID");
				p.setUsername(rone.getString("Username"));
				arr.add(p);
			
			}
			return arr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public void deletPost(int PostID) {
		
		try {
			PreparedStatement ps= con.prepareStatement("delete from Post where post.PostID="+PostID);
			ResultSet rone = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
