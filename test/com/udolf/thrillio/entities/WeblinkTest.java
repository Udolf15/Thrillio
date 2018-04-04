package com.udolf.thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.udolf.thrillio.managers.BookmarkManager;

public class WeblinkTest {

	@Test
	public void testIsKidFriendlyEligible() {
		// test 1 porn in title ...false
		Weblink weblink = BookmarkManager.getInstance().createWeblink(2000, "Taming Tiger,Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",
				"	http://www.javaworld.caom");
		boolean isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertFalse("For Porn in url", isKidFriendlyEligible);
		// test 2 porn in title .....false
		weblink = BookmarkManager.getInstance().createWeblink(2000, "porn,Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",
				"	http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertFalse("For Porn in title", isKidFriendlyEligible);

		// test 3 adult in host .....false
		weblink = BookmarkManager.getInstance().createWeblink(2000, "Taming Tiger,Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",
				"	http://www.adult.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertFalse("For adult in host", isKidFriendlyEligible);

		// test 4 adult in url but not in host part ....true

		weblink = BookmarkManager.getInstance().createWeblink(2000, "Taming Tiger,Part 2",
				"http://adult/article/2072759/core-java/taming--part-2.html",
				"	http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertTrue("For adult in url", isKidFriendlyEligible);

		// test 5 adult in title only ...true

		weblink = BookmarkManager.getInstance().createWeblink(2000, "Taming adult,Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming--part-2.html",
				"	http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertTrue("For adult in title", isKidFriendlyEligible);
	}

}
