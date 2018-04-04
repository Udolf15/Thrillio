package com.udolf.thrillio.constants;

public enum KidFriendlyStatus {
	APPROVED("approved"),
	REJECTED("rejected"),
	UNKNOWN("unknown");
		
		private String status;
		
		private KidFriendlyStatus(String status){
			this.status=status;
		}
}
