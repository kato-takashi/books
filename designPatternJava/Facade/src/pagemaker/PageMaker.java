package pagemaker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PageMaker {
	private PageMaker(){//インスタンスは作らないのでprivate 宣言する
	} 
	public static void makeWelcomePage(String mailaddr, String filename){
		try{
			Properties mailprop = Database.getProperties("maildata");
			String username = mailprop.getProperty(mailaddr);
			HtmlWriter writer = new HtmlWriter(new FileWriter(filename));
			writer.title("Welcom to "+ username + " 'S page!");
			writer.paragraph(username + "のページへようこそ。");
			writer.paragraph("メール待っていますね。");
			writer.mailto(mailaddr, username);
			writer.close();
			System.out.println(filename + " is created for " + "(" + username+")");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
