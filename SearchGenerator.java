
public class SearchGenerator {
	
	// I wanted to make the inner classes implement an interface but then the calls couldn't be static
	
	public static class WalmartSearch {
		private static final String WALMART = "https://walmart.com/search/?query=";
		
		public static String getURL(String item) {
			return WALMART + item;
		}
	}
	
	public static class TargetSearch {
		private static final String TARGET = "";
		
		public String getURL(String item) {
			return "";
		}
	}
	
	public static class MarketStreetSearch {
		private static final String PREFIX = "https://www.marketstreetunited.com/rs/SearchProduct?searchkey=";
		private static final String SUFFIX = "&typeSearch=SearchProducts";
		
		public String getURL(String item) {
			return PREFIX+item+SUFFIX;
		}
	}

	public static class HEBSearch {
		private static final String HEB = "";
		
		public String getURL(String item) {
			return "";
		}
	}
	
}
