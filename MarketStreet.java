//Based off Brian's codejust write " MarketStreet item = new MarketStreet("oranges"); " for example, in the main. 
//And then to get the item price you can set a float = item.itemPrice;
/*

So like:

public static void main(String []args){
      new MarketStreet("milk");
}

Will tell you the first price of milk on our site, but we'd need another program to rapidly collect the prices of all the items listed (which can be recorded on Strings)

*/

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Attributes;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MarketStreet {

    private final String TAG_TYPE = "strong";
    
    private float itemPrice;
    
    public MarketStreet(String item) {
        try {
            itemPrice = getItemPrice(grabPage("https://www.marketstreetunited.com/rs/SearchProduct?searchkey="+item+"&typeSearch=SearchProducts"));
        } catch (IOException e) {
            itemPrice = Float.MAX_VALUE;
            System.out.println("Error getting the value.");
        }
        System.out.println("The price of "+item+" was $" + itemPrice);
    }
    
    private ArrayList<String> searchItem(String item) {
        return new ArrayList<String>();
    }
    
    private Elements grabPage(String url) throws IOException {
        //giving it all the cookies
        return Jsoup.connect(url).cookie("COOKIE_CURRENT_STORE","8").cookie("_pk_id.4.8c4a","b52bfe4ebeccec2d.1612664136.").cookie("COOKIE_CURRENT_PAGE","%2f").cookie("COOKIE_SHOP_PATH","Online").get().getElementsByTag(TAG_TYPE);
    }
    
    private float getItemPrice(Elements el) {
        boolean found = false;
        for(Element e : el) {
            
            String element = e.text();
            if(element.contains("$")) { //it checks for the first element with $ in it and records the price
                if(found==false) {
                    found=true;
                    float price = getRawPrice(element);
                    return price;
                }
            }
        } 
        return 0;
    }
    
    private float getRawPrice(String element){ //removes the dollar sign and letters from an element
      element = element.substring(1); //removes first char ($) from element string
      String temp = "";
      int i = 0;
      while(i<element.length() && ! Character.isLetter(element.charAt(i))){
         temp = temp + element.charAt(i);
         i++;
      }
      element = temp; //reset element to the temp value, this is kind of unecessary but yeah
      return Float.parseFloat(element);
    }
}
