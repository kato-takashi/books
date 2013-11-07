import idcard.IDCardFactory;
import framework.Factory;
import framework.Product;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Factory factory = new IDCardFactory();
		Product card1 = factory.create("加藤貴司");
		Product card2 = factory.create("Chiharu");
		Product card3 = factory.create("Sonoko");
		card1.use();
		card2.use();
		card3.use();

	}

}
