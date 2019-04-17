package com.homework.checklinksfromurl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class checkUrl {
	private String urlAdress;
	String regex = new String("href=\"http(s)?:\\/{2}\\S*\"");
	public checkUrl(String urlAdress) {
		super();
		this.urlAdress = urlAdress;
	}
	
	public void URLcheck () {
		StringBuilder sb = new StringBuilder();
		try {
		
		URL url = new URL(urlAdress);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String text = "";
			for(;(text = br.readLine())!=null;) {
				if (text.contains("href=")) {
					sb.append(text);
				}
			}
			fileWrite(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
	}
	private void fileWrite (String string) {
		try {
			File file = new File("c:\\Users\\white\\eclipse-workspace\\prog.kiev.ua\\src\\com\\homework\\checklinksfromurl\\links.txt");
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(string);
			String temp = "";
			FileWriter writer = new FileWriter(file);
			while(matcher.find()) {
				temp = matcher.group();
				temp = temp.substring(6,temp.length() -1);
				writer.append(temp);
				writer.append("\r\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
