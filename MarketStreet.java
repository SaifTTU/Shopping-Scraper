import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class MarketStreet extends WebScraper {

    private final String TAG_TYPE = "strong";
    private final HashMap cookies;
    
    private float itemPrice;
    
    public MarketStreet(String item) {
    	this.cookies = new HashMap<String, String>();
    	addCookies();
        try {
        	String url = SearchGenerator.MarketStreetSearch.getURL(item);
            this.itemPrice = getItemPrice(grabPage(url, TAG_TYPE, this.cookies));
        } catch (IOException e) {
            this.itemPrice = Float.MAX_VALUE;
            System.out.println("Error getting the value.");
        }
        System.out.println("The price of "+item+" was $" + this.itemPrice);
    }
    
    private void addCookies() {
    	this.cookies.put("COOKIE_CURRENT_STORE","8");
    	this.cookies.put("_pk_id.4.8c4a","b52bfe4ebeccec2d.1612664136.");
    	this.cookies.put("COOKIE_CURRENT_PAGE","%2f");
    	this.cookies.put("COOKIE_SHOP_PATH","Online");
    }
    
    private float getItemPrice(Elements el) {
        boolean found = false;
        for(Element e : el) {
            String element = e.text();
            if(element.contains("$")) { //it checks for the first element with $ in it and records only that price
                if(found==false) {
                    found=true;
                    float price = grabPrice(element);
                    return price;
                }
            }
        } 
        return 0;
    }
    
    private float grabPrice(String element){ //removes the dollar sign and letters from an element
      element = element.substring(1);
      String temp = "";
      int i = 0;
      while(i<element.length() && ! Character.isLetter(element.charAt(i))){
         temp = temp + element.charAt(i);
         i++;
      }
      element = temp;
      return Float.parseFloat(element);
    }
}