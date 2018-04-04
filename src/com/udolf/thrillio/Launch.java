package com.udolf.thrillio;

import java.util.List;

import com.udolf.thrillio.bgjobs.WebPageDownloaderTask;
import com.udolf.thrillio.entities.Bookmark;
import com.udolf.thrillio.entities.User;
import com.udolf.thrillio.managers.BookmarkManager;
import com.udolf.thrillio.managers.UserManager;

public class Launch {
	
	private static List<User> users;
	private static List<List<Bookmark>> bookmarks;
	
	
public static void main(String args[]){
	System.out.println("1 Loading Data..........");
	loadData();
	start();
	
	// background job
	
	runDownloaderJob();
}

private static void runDownloaderJob(){
	WebPageDownloaderTask task=new WebPageDownloaderTask(true);
	(new Thread(task)).start();
}

private static void start() {
	//System.out.println("\n2. loading Data .......");
	
	for(User user:users){
		View.browse(user, bookmarks);
	}
}

private static void loadData() {
	DataStore.loadData();
	users=UserManager.getInstance().getUsers();
	bookmarks=BookmarkManager.getInstance().getBookmark();
	
	//System.out.println("Printing data.........");
	//printUserData();
	//printBookmarkData();
}

private static void printBookmarkData() {
	for(List<Bookmark> bookmarkList:bookmarks){
		for(Bookmark bookmark:bookmarkList){
			System.out.println(bookmark);
		}
	}
	
}

private static void printUserData() {
	for(User user:users){
		System.out.println(user);
	}
	
}
}
