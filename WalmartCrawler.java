
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class WalmartCrawler extends WebScraper implements Crawler{

	private final String TAG_CLASS = "class=\"product-title-link line-clamp line-clamp-2 truncate-title\"";
	private final String BASE_URL = "https://walmart.com";
	private final String NO_RESULTS = "class=\"zero-results-message message active message-warning message-block\"";
	
	private final int MAX_SEARCH_DEPTH = 5;
	private final boolean DEBUG = false;
	private int retrys;
	
	private ArrayList<String> urls;
	private Walmart lowestPrice;
	
	/**
	 * 
	 * @param item The item to be searched for on Walmart
	 */
	public WalmartCrawler(String item) {
		this.retrys = 0;
		this.urls = crawl(SearchGenerator.WalmartSearch.getURL(item));
		if(DEBUG) {
			for(String s : this.urls) {
				System.out.println(s);
			}
		}
		int i=0;
		for(String s : this.urls) {
			if(i>=MAX_SEARCH_DEPTH) break;
			Walmart w = new Walmart(s);
			if(i == 0) {
				this.lowestPrice = w;
			}
			if(w.getPrice() < lowestPrice.getPrice()) {
				this.lowestPrice = w;
			}
			i++;
		}
	}
	
	public ArrayList<String> crawl(String url) {
		if(this.retrys >= 2) {
			System.out.println("Too many retrys... aborting search");
		}
		ArrayList<String> urls = new ArrayList<>();
		Elements e;
		try {
			e = grabPage(url, "a");
		} catch (IOException ex) {
			urls.add("");
			System.out.println("Error crawling the webpage... returning empty string");
			return urls;
		}
		
		for(Element el : e) {
			String s = el.toString();
			if(s.contains(TAG_CLASS)) {
				urls.add((this.BASE_URL + getRawElement(el, "href")));
			}
		}
		
		if(urls.size() == 0) {
			if(noResults(url)) {
				System.out.println("No products matched on Walmart");
			} else {
				this.retrys++;
				crawl(url);
				System.out.println("Trying again...");
			}
		}
		
		return urls;
	}
	
	private boolean noResults(String url) {
		System.out.println("Nothing was added to list. Checking if network error or bad search term");
		Elements e;
		try {
			e = grabPage(url, "span");
		} catch (IOException ex) {
			System.out.println("Error crawling the webpage...");
			return true;
		}
		
		for(Element el : e) {
			String s = el.toString();
			if(s.contains(NO_RESULTS)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Walmart getLowestPrice {
		return this.lowestPrice();
	}
	
}
