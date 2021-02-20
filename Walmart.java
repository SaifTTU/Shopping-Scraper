
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Walmart extends WebScraper {

	private final String SINGLE_PRICE_ELEMENT_ATTRIBUTES = "class=\"price-characteristic\" itemprop=\"price\" content=";
	private final String MULTI_PRICE_ELEMENT_ATTRIBUTES = "class=\"price-characteristic\" itemprop=\"lowPrice\" content=";
	private final String TAG_TYPE = "span";
	
	private float itemPrice;
	
	public Walmart(String url) {
		try {
			this.itemPrice = getItemPrice(grabPage(url, TAG_TYPE));
		} catch (IOException e) {
			this.itemPrice = Float.MAX_VALUE;
			System.out.println("Error getting the value.");
		}
		System.out.println("The price of the item was $" + this.itemPrice);
	}
	
	private float getItemPrice(Elements el) {
		for(Element e : el) {
			String element = e.toString();
			if(element.contains(SINGLE_PRICE_ELEMENT_ATTRIBUTES) || element.contains(MULTI_PRICE_ELEMENT_ATTRIBUTES)) {
				try {
					float price = Float.parseFloat(getRawElement(e, "content"));
					return price;
				} catch (Exception ex) {
					
				}
			}
		}
		return 0;
	}
	
}
