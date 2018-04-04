package com.udolf.thrillio.managers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import com.udolf.thrillio.constants.BookGenre;
import com.udolf.thrillio.constants.KidFriendlyStatus;
import com.udolf.thrillio.constants.MovieGenre;
import com.udolf.thrillio.dao.BookmarkDao;
import com.udolf.thrillio.entities.Book;
import com.udolf.thrillio.entities.Bookmark;
import com.udolf.thrillio.entities.Movie;
import com.udolf.thrillio.entities.User;
import com.udolf.thrillio.entities.Weblink;
import com.udolf.thrillio.entities.userBookmark;
import com.udolf.thrillio.util.HttpConnect;
import com.udolf.thrillio.util.IOUtil;

public class BookmarkManager {

	private static BookmarkManager instance = new BookmarkManager();

	private BookmarkManager() {
	}

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String profileURL, String title, int releaseYear, String[] cast,
			String[] directors, MovieGenre genre, double imdbRating) {
		Movie movie = new Movie();
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenere(genre);
		movie.setId(id);
		movie.setImdbRating(imdbRating);
		movie.setProfileURL(profileURL);
		movie.setReleaseYear(releaseYear);
		movie.setTitle(title);

		return movie;
	}

	public Book createBook(long id, String profileURL, String title, int publicationYear, String publisher,
			String[] authors, BookGenre genre, double amazonRating) {
		Book book = new Book();
		book.setAmazonRating(amazonRating);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setId(id);
		book.setProfileURL(profileURL);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setTitle(title);

		return book;
	}
	
	public Weblink createWeblink(long id, String title,String URL,String host){
		Weblink weblink=new Weblink();
		weblink.setHost(host);
		weblink.setId(id);
		weblink.setTitle(title);
		weblink.setURL(URL);
		
		return weblink;
	}
	
	private static BookmarkDao dao=new BookmarkDao();
	
	public List<List<Bookmark>> getBookmark(){
		return dao.getBookmark();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
	userBookmark userBookmark=new userBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);
		
		/*if(bookmark instanceof Weblink){
			String url=((Weblink)bookmark).getURL();
			if(url!=".pdf"){
				try {
					String webPage=HttpConnect.download(url);
					if(webPage!=null){
						IOUtil.write(webPage,bookmark.getId());
					}
				} catch (MalformedURLException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		*/
		
		dao.saveUserBookmark(userBookmark);
	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
		bookmark.setKidFriendlMarkedBy(user);
		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		dao.updateKidFriendlyStatus(bookmark);
		System.out.println("kid Friendly Status : "+kidFriendlyStatus+" "+", Marked By: "+user.getEmail()+" , "+bookmark);
	}

	public void share(User user, Bookmark bookmark) {
		bookmark.setSharedBy(user);
		
		System.out.println("Data to be shared by");
		if(bookmark instanceof Book){
			System.out.println(((Book)bookmark).getItemData());
		}else if(bookmark instanceof Weblink){
			System.out.println(((Weblink)bookmark).getItemData());
		}
		
		dao.sharedByInfo(bookmark);
		
	}
}
