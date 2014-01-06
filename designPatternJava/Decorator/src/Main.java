
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Display b1 = new StringDisplay("Hello, World.");
		Display b2 = new SideBorder(b1, '#');
		Display b3 = new FullBorder(b2);
		b1.show();
		b2.show();
		b3.show();

		Display b4 = new SideBorder(new FullBorder(
				new FullBorder(
						new SideBorder(
								new FullBorder(
										new StringDisplay("こんにちわ")
										), 
										'*'
								)
						)
				), 
				'/'
				);
		b4.show();
	}

}
