import java.io.IOException;


public class Main {
public static void main(String[] args){
		FileIO f = new FileProperties();
		try{
			f.readFromFile("file.txt");
			f.setValue("year", "2013");
			f.setValue("month", "10");
			f.setValue("day", "17");
			f.writeToFile("newfile.txt");
		}catch(IOException e){
			e.printStackTrace();
		}
}
}
