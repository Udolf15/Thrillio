package com.udolf.thrillio;

import java.util.List;

import com.udolf.thrillio.constants.KidFriendlyStatus;
import com.udolf.thrillio.constants.userType;
import com.udolf.thrillio.controllers.BookmarkController;
import com.udolf.thrillio.entities.Bookmark;
import com.udolf.thrillio.entities.User;
import com.udolf.thrillio.partner.Shareable;

public class View {

	public static void browse(User user,List<List<Bookmark>> bookmarks) {
		System.out.println("\n" + user.getEmail() + "is browsing items...");
		int bookmarkCount = 0;

		for (List<Bookmark> bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				
					boolean isBookmarked = getBookmarkDecision(bookmark);
					if (isBookmarked) {
						bookmarkCount++;
						BookmarkController.getInstance().saveUserbookmark(user, bookmark);
						System.out.println("New Item Bookmarked - " + bookmark);
					}
				

				if (user.getUserType().equals(userType.EDITOR) || user.getUserType().equals(userType.CHIEF_EDITOR)) {

					// Mark as kid friendly

					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						KidFriendlyStatus kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
						if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
						}
					}
							if(bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED) && bookmark instanceof Shareable){
								boolean isShare=getShareDecision();
								if(isShare){
							BookmarkController.getInstance().share(user,bookmark);
								}
							}
				}
			}

		}
	}

	// TODO below method we will take input
	
	private static boolean getShareDecision() {
		return Math.random() < .5 ? true : false;
	}

	private static KidFriendlyStatus getKidFriendlyStatusDecision(Bookmark bookmark) {
		double decision =Math.random();
		return decision < .4 ? (KidFriendlyStatus.APPROVED)
				: (decision >= .4 && Math.random() < .8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;

	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < .5 ? true : false;
	}

	/*
	 * public static void bookmark(User user,Bookmark[][] bookmarks){
	 * System.out.println("\n"+user.getEmail()+"is Bookmarking"); for(int
	 * i=0;i<DataStore.USER_BOOKMARK_LIMIT;i++){ int
	 * typeOffset=(int)(Math.random()*DataStore.BOOKMARK_TYPES_COUNT); int
	 * bookmarkOffset=(int)(Math.random()*DataStore.BOOKMARK_COUNT_PER_TYPE);
	 * Bookmark bookmark=bookmarks[typeOffset][bookmarkOffset];
	 * BookmarkController.getInstance().saveUserbookmark(user, bookmark);
	 * System.out.println(bookmark); } }
	 */
}
