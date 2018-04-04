package com.udolf.thrillio.entities;

import com.udolf.thrillio.constants.BookGenre;
import com.udolf.thrillio.managers.BookmarkManager;

import junit.framework.TestCase;

public class BookTest extends TestCase {

	public void testIsKidFriendlyEligible() {
		// test 1 genre== philosophy ..false
		
	Book book=BookmarkManager.getInstance().createBook(4000, "Walden", "", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
		
	boolean isKidFriendlyEligible=book.isKidFriendlyEligible();
	
	assertFalse("Philosophy in genre",isKidFriendlyEligible);
	
	// test 2 genre == SELF_HELP  ....false
	
	 book=BookmarkManager.getInstance().createBook(4000, "Walden", "", 1854, "Wilder Publications",
			new String[] { "Henry David Thoreau" }, BookGenre.SELF_HELP, 4.3);
	
	 isKidFriendlyEligible=book.isKidFriendlyEligible();

assertFalse("Self_Help in genre",isKidFriendlyEligible);
		
	}

}
