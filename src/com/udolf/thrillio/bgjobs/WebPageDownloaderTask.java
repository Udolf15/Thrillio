package com.udolf.thrillio.bgjobs;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.udolf.thrillio.dao.BookmarkDao;
import com.udolf.thrillio.entities.Weblink;
import com.udolf.thrillio.entities.Weblink.DownloadStatus;
import com.udolf.thrillio.util.HttpConnect;
import com.udolf.thrillio.util.IOUtil;

public class WebPageDownloaderTask implements Runnable{

	public static BookmarkDao dao=new BookmarkDao();
	private static final long TIME_FRAME=3000000000L;
	private boolean downloadAll=false;
	ExecutorService downloadExecuter=Executors.newFixedThreadPool(5);
	
	private static class Downloader<T extends Weblink> implements Callable<T>{
		private T weblink;
		public Downloader(T weblink){
			this.weblink=weblink;
		}
		@Override
		public T call(){
			// TODO Auto-generated method stub
			
			
				String htmlPage;
				try {
					if(!weblink.getURL().endsWith("pdf")){
					htmlPage = HttpConnect.download(weblink.getURL());
					weblink.setHtmlPage(htmlPage);
					weblink.setDownloadStatus(Weblink.DownloadStatus.FAILED);
					}else{
						weblink.setDownloadStatus(Weblink.DownloadStatus.NOT_ELIGIBLE);
					}
				} catch (MalformedURLException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			return weblink;
		}
		
	}
	
	
	public WebPageDownloaderTask(boolean downloadAll){
		this.downloadAll=downloadAll;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(Thread.currentThread().isInterrupted()){
			List<Weblink> weblinks=getWebLinks();
			
		
		if(weblinks.size()>0){
			download(weblinks);
		}else{
			System.out.println("no weblinks to download");
		}
		
		try {
			TimeUnit.SECONDS.sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
private void download(List<Weblink> weblinks) {
		List<Downloader<Weblink>> task=getTasks(weblinks);
		List<Future<Weblink>> futures=new ArrayList<>();
		
		try {
			futures =downloadExecuter.invokeAll(task,TIME_FRAME,TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Future<Weblink> future:futures){
			
				 try {
					 if(!future.isCancelled()){
					Weblink weblink=future.get();
					String webPage=weblink.getHtmlPage();
					if(webPage!=null){
						IOUtil.write(webPage, weblink.getId());
						weblink.setDownloadStatus(Weblink.DownloadStatus.SUCCESS);
						System.out.println("Successfully downloaded "+weblink.getURL());
					}else{
						weblink.setDownloadStatus(Weblink.DownloadStatus.FAILED);
						System.out.println("Failed  "+weblink.getURL());
					}
					 }
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
		}
	}

private List<Downloader<Weblink>> getTasks(List<Weblink> weblinks) {
	List<Downloader<Weblink>> tasks=new ArrayList<>();
	
	for(Weblink weblink:weblinks){
		tasks.add(new Downloader<Weblink>(weblink));
	}
	
	return tasks;
}

private List<Weblink> getWebLinks(){
	List<Weblink> weblinks=null;
	if(downloadAll){
		dao.getAllWeblinks();
		downloadAll=false;
	}else{
		dao.getWeblinks(DownloadStatus.NOT_ATTEMPTED);
	}
	return weblinks;
}
	
}
