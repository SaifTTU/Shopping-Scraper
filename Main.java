
public class Main {

	public static void main(String[] args) {
		
		WalmartCrawler c = new WalmartCrawler("bananas");
		System.out.println("--------------------");
//		c = new WalmartCrawler("");
		System.out.println("--------------------");
//		c = new WalmartCrawler("rug");
		System.out.println("--------------------");
		c = new WalmartCrawler("asd;flkj");
                
                Target t_test = new Target("https://www.target.com/p/make-a-size-paper-towels-2-big-rolls-smartly-8482/-/A-75557222");
	}
	
	public static void runTests() {
		String bananas = "Bananas";
		String iceCream = "Ice Cream";
		String jibberish = "asdfjkl;";
	}
}
