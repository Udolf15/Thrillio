package com.udolf.thrillio.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.udolf.thrillio.DataStore;
import com.udolf.thrillio.constants.KidFriendlyStatus;
import com.udolf.thrillio.entities.Book;
import com.udolf.thrillio.entities.Bookmark;
import com.udolf.thrillio.entities.Movie;
import com.udolf.thrillio.entities.Weblink;
import com.udolf.thrillio.entities.userBookmark;

public class BookmarkDao {
	public List<List<Bookmark>> getBookmark() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(userBookmark userBookmark) {
		// DataStore.add(userBookmark);

		try (Connection conn = (Connection) DriverManager
				.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "wanrltw69");
				Statement stmt = (Statement) conn.createStatement();) {
			if (userBookmark.getBookmark() instanceof Book) {
				saveUserBook(userBookmark, stmt);
			} else if (userBookmark.getBookmark() instanceof Movie) {
				saveUserMovie(userBookmark, stmt);
			} else {
				saveUserWeblink(userBookmark, stmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void saveUserBook(userBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into User_Book ( user_id, book_id) values (" + userBookmark.getUser().getId() + ","
				+ userBookmark.getBookmark().getId() + ")";
		stmt.executeUpdate(query);
	}

	private void saveUserMovie(userBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into User_Movie ( user_id, movie_id) values (" + userBookmark.getUser().getId() + ","
				+ userBookmark.getBookmark().getId() + ")";
		stmt.executeUpdate(query);
	}

	private void saveUserWeblink(userBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into User_Weblink ( user_id, weblink_id) values (" + userBookmark.getUser().getId() + ","
				+ userBookmark.getBookmark().getId() + ")";
		stmt.executeUpdate(query);

	}

	public List<Weblink> getAllWeblinks() {
		List<Weblink> result = new ArrayList<>();
		List<List<Bookmark>> bookmarks = DataStore.getBookmarks();

		List<Bookmark> allWeblinks = bookmarks.get(0);

		for (Bookmark bookmark : allWeblinks) {
			result.add((Weblink) bookmark);
		}

		return result;
	}

	public List<Weblink> getWeblinks(Weblink.DownloadStatus downloadStatus) {
		List<Weblink> result = new ArrayList<>();
		List<Weblink> allWeblinks = getAllWeblinks();

		for (Weblink weblink : allWeblinks) {
			if (weblink.getDownloadStatus().equals(downloadStatus)) {
				result.add(weblink);
			}
		}

		return result;

	}

	public void updateKidFriendlyStatus(Bookmark bookmark) {
		int KidFriendlyStatus = bookmark.getKidFriendlyStatus().ordinal();
		long userId = bookmark.getKidFriendlMarkedBy().getId();

		String tableToUpdate = "Book";
		if (bookmark instanceof Movie) {
			tableToUpdate = "Movie";
		} else if (bookmark instanceof Weblink) {
			tableToUpdate = "WebLink";
		}

		try (Connection conn = (Connection) DriverManager
				.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "wanrltw69");
				Statement stmt = (Statement) conn.createStatement();) {
			String query = "update " + tableToUpdate + " set kid_friendly_status =" + KidFriendlyStatus
					+ ", kid_friendly_marked_by =" + userId + " where id =" + bookmark.getId();
			
			System.out.println("inside "+query);
			stmt.executeUpdate(query);
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void sharedByInfo(Bookmark bookmark) {
		
		long userId = bookmark.getSharedBy().getId();
		String tableToUpdate = "Book";
		 if (bookmark instanceof Weblink) {
			tableToUpdate = "WebLink";
		}

		try (Connection conn = (Connection) DriverManager
				.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "wanrltw69");
				Statement stmt = (Statement) conn.createStatement();) {
			String query = "update " + tableToUpdate + " set shared_by =" + userId+
					 " where id =" + bookmark.getId();
			
			System.out.println("inside your mamma ass");
			stmt.executeUpdate(query);
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
