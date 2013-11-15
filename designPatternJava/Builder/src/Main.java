
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length != 1){
			usage();
			System.exit(0);
		}
		
		if(args[0].equals("plain")){
			TextBuilder textbuilder = new TextBuilder();
			Director director = new Director(textbuilder);
		}
	}

}
