
import java.util.ArrayList;

public interface Crawler {

	/** 
	 * Crawls the URL to collect a list of the top 5 items
	 * @param url The URL that needs to be crawled
	 * @return The URL of the items
	 */
	public ArrayList<String> crawl(String url);
	
}
