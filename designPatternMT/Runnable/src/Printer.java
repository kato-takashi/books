
public class Printer implements Runnable {
	private String messageString;
	public Printer(String message){
		this.messageString = message;
	}
	
	public void run(){
		for(int i=0; i< 1000; i++){
			System.out.print(messageString);
		}
	}
}
