
public class Main {
	public static void main(String[] args){
		if(args.length == 0){
			System.out.println("Usage: java Main digits");
			System.out.println("Exmple: java main 1212123");
			System.exit(0);
		}

		BigString bsBigString = new BigString(args[0]);
		bsBigString.print();
	}
}
