package com.udolf.thrillio.controllers;

import com.udolf.thrillio.constants.KidFriendlyStatus;
import com.udolf.thrillio.entities.Bookmark;
import com.udolf.thrillio.entities.User;
import com.udolf.thrillio.managers.BookmarkManager;

public class BookmarkController {
private static BookmarkController instance=new BookmarkController();
private BookmarkController(){}
public static BookmarkController getInstance(){
	return instance;
}
public void saveUserbookmark(User user,Bookmark bookmark){
BookmarkManager.getInstance().saveUserBookmark(user,bookmark);
}
public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
	BookmarkManager.getInstance().setKidFriendlyStatus(user,kidFriendlyStatus,bookmark);
}
public void share(User user, Bookmark bookmark) {
	// TODO Auto-generated method stub
	BookmarkManager.getInstance().share(user,bookmark);
}
}
