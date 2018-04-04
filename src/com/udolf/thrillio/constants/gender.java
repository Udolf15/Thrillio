package com.udolf.thrillio.constants;

public enum gender {

 MALE(0),
 FEMALE(1),
 TRANSGENDER(2);
	
	private int value;
	
	private gender(int value){
		this.value=value;
	}
	
	public int getGender(){
		return value;
	}
}
