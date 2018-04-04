package com.udolf.thrillio.entities;

import org.apache.commons.lang3.StringUtils;

import com.udolf.thrillio.partner.Shareable;

public class Weblink extends Bookmark implements Shareable{
	private String URL;
	private String host;
	private String htmlPage;
	private DownloadStatus downloadStatus=DownloadStatus.NOT_ATTEMPTED;
	
	public enum DownloadStatus{
		NOT_ATTEMPTED,SUCCESS,FAILED,NOT_ELIGIBLE;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "Weblink [URL=" + URL + ", host=" + host + "]";
	}

	@Override
	public boolean isKidFriendlyEligible() {
		
		if(URL.contains("porn")||getTitle().contains("porn")||host.contains("adult")){
			return false;
		}
		else{
			return true;
		}
	}
	
	public String getItemData() {
		StringBuilder builder=new StringBuilder();
		builder.append("<item>");
		builder.append("<type>Weblink</type>");
		builder.append("<title>").append(getTitle()).append("</title>");
		builder.append("<URL>").append(URL).append("</URL>");
		builder.append("<host>").append(host).append("/<host>");
		builder.append("</item>");
		return builder.toString();
	}

	public String getHtmlPage() {
		return htmlPage;
	}

	public void setHtmlPage(String htmlPage) {
		this.htmlPage = htmlPage;
	}

	public DownloadStatus getDownloadStatus() {
		return downloadStatus;
	}

	public void setDownloadStatus(DownloadStatus downloadStatus) {
		this.downloadStatus = downloadStatus;
	}

}
