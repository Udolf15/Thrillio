package com.udolf.thrillio.constants;

public enum BookGenre {
 ART("art"),
 BIOGRAPHY("biography"),
 CHILDREN("children"),
 FICTION("fiction"),
 HISTORY("history"),
 MYSTERY("mystery"),
 PHILOSOPHY("philosophy"),
 RELEGION("relegion"),
 ROMANCE("romance"),
 SELF_HELP("selfHelp"),
 TECHNICAL("technical");
 
	private String name;
		
	
 private BookGenre(String name){
	 this.name=name;
 }
 
 public String getName(){
	 return name;
 }
}
