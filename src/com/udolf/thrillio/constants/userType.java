package com.udolf.thrillio.constants;

public enum userType {
  USER("user"),
 EDITOR("editor"),
  CHIEF_EDITOR("chiefEditor");
	
	private String name;
	private userType(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}

}
