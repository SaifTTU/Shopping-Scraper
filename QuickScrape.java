/*
// Jeff Tessitore Jr.
// Project Name -- 
 */

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Attributes;
import org.jsoup.select.Elements;

/**
 *
 * @author Jeff Tessitore Jr.
 */
public class QuickScrape {
    
    protected Elements grabPage(String url, String tag) throws IOException {
        return Jsoup.connect(url).get().getElementsByTag(tag);
    }
    
    protected String getRawElement(Element e, String attr) {
    	return e.text().replace("$","");
    }
    
}
