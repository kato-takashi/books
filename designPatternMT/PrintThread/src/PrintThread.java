import javax.lang.model.element.VariableElement;


public class PrintThread extends Thread{
	private String messageString;
	public PrintThread(String messageString){
		this.messageString = messageString;
	}
	
	public void run() {
		for(int i=0; i<1000; i++){
			System.out.print(messageString);
		}
	}
}
