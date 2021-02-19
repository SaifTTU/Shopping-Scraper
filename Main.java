
public class Main {

	public static void main(String[] args) {
		
		WalmartCrawler c = new WalmartCrawler("bananas");
		System.out.println("--------------------");
//		c = new WalmartCrawler("");
		System.out.println("--------------------");
//		c = new WalmartCrawler("rug");
		System.out.println("--------------------");
		c = new WalmartCrawler("asd;flkj");
	}
	
	public static void runTests() {
		String bananas = "Bananas";
		String iceCream = "Ice Cream";
		String jibberish = "asdfjkl;";
	}
}
