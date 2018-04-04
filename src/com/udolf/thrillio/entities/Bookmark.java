package com.udolf.thrillio.entities;

import com.udolf.thrillio.constants.KidFriendlyStatus;

public abstract class Bookmark {
	private long id;
	private String profileURL;
	private String title;
	private KidFriendlyStatus kidFriendlyStatus=KidFriendlyStatus.UNKNOWN;
	private User kidFriendlMarkedBy;
	private User sharedBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProfileURL() {
		return profileURL;
	}

	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public abstract boolean isKidFriendlyEligible();

	public KidFriendlyStatus getKidFriendlyStatus() {
		return kidFriendlyStatus;
	}

	public void setKidFriendlyStatus(KidFriendlyStatus kidFriendlyStatus) {
		this.kidFriendlyStatus = kidFriendlyStatus;
	}

	public User getKidFriendlMarkedBy() {
		return kidFriendlMarkedBy;
	}

	public void setKidFriendlMarkedBy(User kidFriendlMarkedBy) {
		this.kidFriendlMarkedBy = kidFriendlMarkedBy;
	}

	public User getSharedBy() {
		return sharedBy;
	}

	public void setSharedBy(User sharedBy) {
		this.sharedBy = sharedBy;
	}
}
