
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Attributes;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class WebScraper {
	
	protected Elements grabPage(String url, String tag, HashMap<String, String> c) throws IOException {
		return Jsoup.connect(url).cookies(c).get().getElementsByTag(tag);
	}
	
	protected Elements grabPage(String url, String tag) throws IOException{
		HashMap c = new HashMap<String, String>();
		return grabPage(url, tag, c);
	}
	
	protected String getRawElement(Element e, String attr) {
		Attributes a = e.attributes();
		return a.get(attr);
	}

}
