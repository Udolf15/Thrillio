package com.udolf.thrillio.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class IOUtil {

	public static void read(List<String> data,String fileName){
		try(BufferedReader buf=new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"))){
			String line;
			int count=0;
			while((line=buf.readLine())!=null){
				data.add(line);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String read(InputStream in) {
		StringBuilder text=new StringBuilder();
		try(BufferedReader buf=new BufferedReader(new InputStreamReader(in,"UTF-8"))){
			String line;
			while((line=buf.readLine())!=null){
				text.append(line).append("\n");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text.toString();
	}

	public static void write(String webPage, long id) {
		// TODO Auto-generated method stub
		try(BufferedWriter buf=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/Work and Projects/Java Work/thrillio/pages/"+String.valueOf(id)+".html"),"UTF-8"))){
		try {
			buf.write(webPage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
}
