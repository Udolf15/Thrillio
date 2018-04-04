package com.udolf.thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.udolf.thrillio.constants.MovieGenre;
import com.udolf.thrillio.managers.BookmarkManager;

public class MovieTest {

	@Test
	public void testIsKidFriendlyEligible() {
		// test 1 if genre contains horror .. false
		Movie movie=BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.HORROR, 8.5);
		
		boolean isKidFriendlyEligible=movie.isKidFriendlyEligible();
		assertFalse("Horror in genre",isKidFriendlyEligible);
		
		//test 2 if genre is thriller .. false
		
		movie=BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.THRILLERS, 8.5);
		
		isKidFriendlyEligible=movie.isKidFriendlyEligible();
		assertFalse("Horror in genre",isKidFriendlyEligible);
	}

}
