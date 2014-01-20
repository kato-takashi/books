package observer;

public class Main {

	public static void main(String[] args) {
		NumberGenerator generator = new RandomNumberGenerator();
		Observer obsetver1 = new DigitObserver();
		Observer obsetver2 = new GraphObserver();
		generator.addObserver(obsetver1);
		generator.addObserver(obsetver2);
		generator.execute();
	}
}
