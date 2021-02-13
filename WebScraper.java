
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Attributes;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebScraper {
	
	protected Elements grabPage(String url, String tag) throws IOException {
		return Jsoup.connect(url).get().getElementsByTag(tag);
	}
	
	protected String getRawElement(Element e, String attr) {
		Attributes a = e.attributes();
		return a.get(attr);
	}

}
