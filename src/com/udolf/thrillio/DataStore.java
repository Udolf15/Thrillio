package com.udolf.thrillio;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.udolf.thrillio.constants.BookGenre;
import com.udolf.thrillio.constants.MovieGenre;
import com.udolf.thrillio.constants.gender;
import com.udolf.thrillio.constants.userType;
import com.udolf.thrillio.entities.Bookmark;
import com.udolf.thrillio.entities.User;
import com.udolf.thrillio.entities.userBookmark;
import com.udolf.thrillio.managers.BookmarkManager;
import com.udolf.thrillio.managers.UserManager;

public class DataStore {
	
	private static List<User> users = new ArrayList<>();
	public static List<User> getUsers() {
		return users;
	}

	private static List<List<Bookmark>> bookmarks = new ArrayList<>();
	public static List<List<Bookmark>> getBookmarks() {
		return bookmarks;
	}

	private static List<userBookmark> userBookmarks = new ArrayList<>();
	
	public static void loadData() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//new com.mysql.jdbc.Driver(); 
			            // OR
			//System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
		
		                // OR java.sql.DriverManager
		    //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// try-with-resources ==> conn & stmt will be closed
		// Connection string: <protocol>:<sub-protocol>:<data-source details>
		try (Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "wanrltw69");
				Statement stmt = (Statement) conn.createStatement();) {
			loadUsers(stmt);    // Student is expected to implement this method
			loadWebLinks(stmt); // Student is expected to implement this method
			loadMovies(stmt);
			loadBooks(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

	
	private static void loadUsers(Statement stmt) throws SQLException {
		String query ="Select * from User";
		ResultSet rs=stmt.executeQuery(query);
		
		while(rs.next()){
			long id=rs.getLong("id");
			String email=rs.getString("email");
			String firstName=rs.getString("first_name");
			String lastName=rs.getString("last_name");
			int gender_id=rs.getInt("gender_id");
			gender gen=gender.values()[gender_id];
			String password=rs.getString("password");
			int user_type_id=rs.getInt("user_type_id");
			userType user=userType.values()[user_type_id];
			
			
			User userAdder=UserManager.getInstance().createUser(id, email, firstName, lastName, gen, password, user);
			users.add(userAdder);
		}
		
	}


	private static void loadWebLinks(Statement stmt) throws SQLException {
		String query ="Select * from WebLink";
		ResultSet rs=stmt.executeQuery(query);
		
		List<Bookmark> bookmarkList=new ArrayList<>();
		
		while(rs.next()){
			long id=rs.getLong("id");
			String title=rs.getString("title");
			String url=rs.getString("url");
			String host=rs.getString("host");
			
			Bookmark bookmark=BookmarkManager.getInstance().createWeblink(id, title, url, host);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}


	private static void loadMovies(Statement stmt) throws SQLException {
		String query = "Select m.id, title, release_year, GROUP_CONCAT(a.name SEPARATOR ',') AS cast, GROUP_CONCAT(d.name SEPARATOR ',') AS directors, movie_genre_id, imdb_rating"
				+ " from Movie m, Actor a, Movie_Actor ma, Director d, Movie_Director md "
				+ "where m.id = ma.movie_id and ma.actor_id = a.id and "
				      + "m.id = md.movie_id and md.director_id = md.id group by m.id";
		ResultSet rs = stmt.executeQuery(query);
		
		List<Bookmark> bookmarkList = new ArrayList<>();
		while (rs.next()) {
			long id = rs.getLong("id");
			String title = rs.getString("title");
			int releaseYear = rs.getInt("release_year");
			String[] cast = rs.getString("cast").split(",");
			String[] directors = rs.getString("directors").split(",");			
			int genre_id = rs.getInt("movie_genre_id");
			MovieGenre genre = MovieGenre.values()[genre_id];
			double imdbRating = rs.getDouble("imdb_rating");
			
			Bookmark bookmark = BookmarkManager.getInstance().createMovie(id, title, "", releaseYear, cast, directors, genre, imdbRating/*, values[7]*/);
    		bookmarkList.add(bookmark); 
		}
		bookmarks.add(bookmarkList);
	}
	
	private static void loadBooks(Statement stmt) throws SQLException {		    	
		String query = "Select b.id, title, publication_year, p.name, GROUP_CONCAT(a.name SEPARATOR ',') AS authors, book_genre_id, amazon_rating, created_date"
				+ " from Book b, Publisher p, Author a, Book_Author ba "
				+ "where b.publisher_id = p.id and b.id = ba.book_id and ba.author_id = a.id group by b.id";
    	ResultSet rs = stmt.executeQuery(query);
		
    	List<Bookmark> bookmarkList = new ArrayList<>();
    	while (rs.next()) {
    		long id = rs.getLong("id");
			String title = rs.getString("title");
			String profileURL=rs.getString("profileURL");
			int publicationYear = rs.getInt("publication_year");
			String publisher = rs.getString("name");		
			String[] authors = rs.getString("authors").split(",");			
			int genre_id = rs.getInt("book_genre_id");
			BookGenre genre = BookGenre.values()[genre_id];
			double amazonRating = rs.getDouble("amazon_rating");
			
			Date createdDate = rs.getDate("created_date");
			System.out.println("createdDate: " + createdDate);
			Timestamp timeStamp = rs.getTimestamp(8);
			System.out.println("timeStamp: " + timeStamp);
			System.out.println("localDateTime: " + timeStamp.toLocalDateTime());
			
			System.out.println("id: " + id + ", title: " + title + ", publication year: " + publicationYear + ", publisher: " + publisher + ", authors: " + String.join(", ", authors) + ", genre: " + genre + ", amazonRating: " + amazonRating);
    		
    		Bookmark bookmark = BookmarkManager.getInstance().createBook(id, profileURL,title, publicationYear, publisher, authors, genre, amazonRating/*, values[7]*/);
    		bookmarkList.add(bookmark); 
    	}
    	bookmarks.add(bookmarkList);
    }	
	
	public static void add(userBookmark userBookmark) {
		userBookmarks.add(userBookmark);		
	}	
}