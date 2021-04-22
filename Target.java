

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
// Jeff Tessitore Jr.
// Project Name -- 
 */

/**
 *
 * @author parra
 */
public class Target extends QuickScrape {
    
    private final String SINGLE_PRICE_ATTR = "class=\"style__PriceFontSize-sc-17wlxvr-0 ceEMdT\" data-test=\"product-price\"";
    private final String TAG_TYPE = "div";
    
    private float iPrice;
    
    public Target(String url) {
        try {
            iPrice = getItemPrice(grabPage(url,TAG_TYPE));
        } catch (IOException e) {
            iPrice = Float.MAX_VALUE;
            System.out.println("The price could not be found.");
        }
        System.out.println("The price of the item was $" + iPrice);
    }
    
    private float getItemPrice(Elements el) {
        for (Element e : el) {
            String element = e.toString();
            if (element.contains(SINGLE_PRICE_ATTR)) {
                try {
                    float price = Float.parseFloat(getRawElement(e, "product-price"));
                    return price;
                } catch (Exception ex) {
                	System.out.println(ex);
                }
            }
        }
        return (float)(0.0);
    }
}
