package com.udolf.thrillio.entities;

import java.util.Arrays;

import com.udolf.thrillio.constants.MovieGenre;

public class Movie extends Bookmark {

	private int releaseYear;
	private String[] cast;
	private String[] directors;
	private MovieGenre genere;
	private double imdbRating;

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String[] getCast() {
		return cast;
	}

	public void setCast(String[] cast) {
		this.cast = cast;
	}

	public String[] getDirectors() {
		return directors;
	}

	public void setDirectors(String[] directors) {
		this.directors = directors;
	}

	public MovieGenre getGenere() {
		return genere;
	}

	public void setGenere(MovieGenre genere) {
		this.genere = genere;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}

	@Override
	public String toString() {
		return "Movie [releaseYear=" + releaseYear + ", cast=" + Arrays.toString(cast) + ", directors="
				+ Arrays.toString(directors) + ", genere=" + genere + ", imdbRating=" + imdbRating + "]";
	}

	@Override
	public boolean isKidFriendlyEligible() {
		if(getGenere().equals(MovieGenre.HORROR)||getGenere().equals(MovieGenre.THRILLERS)){
			return false;
		}else{
			return true;
		}
	}



}
